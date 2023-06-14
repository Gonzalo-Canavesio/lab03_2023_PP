package src;

import src.parser.feedParser.RedditParser;
import src.parser.feedParser.RssParser;
import src.parser.subscriptionParser.JSONParser;

import src.subscription.*;
import src.httpRequest.*;
import src.namedEntity.CreadorEntidades;
import src.namedEntity.EntidadNombrada;
import src.namedEntity.heuristic.Heuristic;
import src.namedEntity.heuristic.QuickHeuristic;
import src.feed.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;

import scala.Tuple2;

public class FeedReaderMain {

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne|-search]");
    }

    private static Feed doParse(RoughFeed roughFeed) {
        if (roughFeed.getUrlType().equals("rss")) {
            RssParser result = new RssParser();
            return result.parse(roughFeed);
        } else if (roughFeed.getUrlType().equals("reddit")) {
            RedditParser result = new RedditParser();
            return result.parse(roughFeed);
        } else {
            System.out.println("Invalid feed type");
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");

        // Leer el archivo de suscription por defecto y parsearlo
        JSONParser subscriptionParser = new JSONParser();
        Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

        // Llamar al httpRequester para obtener el feed del servidor
        httpRequester httpRequester = new httpRequester(subscription);
        List<RoughFeed> roughFeeds = httpRequester.getFeeds();

        // Crear el contexto de Spark
        SparkConf conf = new SparkConf().setAppName("FeedReader").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Crear el RDD de feeds
        JavaRDD<RoughFeed> roughFeedsRDD = sc.parallelize(roughFeeds);

        // Crear el RDD de feeds parseados
        JavaRDD<Feed> feedsRDD = roughFeedsRDD.map(roughFeed -> doParse(roughFeed));

        if (args.length == 0) {
            // Imprimir los feeds parseados
            feedsRDD.foreach(feed -> feed.prettyPrint());

            sc.close();

        } else if (args.length == 1 && args[0].equals("-ne")) {
            // Crear el RDD de articulos
            JavaRDD<Article> articlesRDD = feedsRDD.flatMap(feed -> feed.getArticleList().iterator());

            // Crear el RDD de entidades nombradas
            Heuristic heuristica = new QuickHeuristic();
            JavaRDD<String> namedEntitiesRDD = articlesRDD.flatMap(article -> {
                if (article.getType() == "rss") {
                    return article.computeNamedEntities(heuristica).iterator();
                } else {
                    return new ArrayList<String>().iterator();
                }
            });

            // Crear el RDD de entidades nombradas con frecuencia
            JavaPairRDD<String, Integer> namedEntitiesFrecuencyRDD = namedEntitiesRDD
                    .mapToPair(namedEntity -> new Tuple2<>(namedEntity, 1));
            JavaPairRDD<String, Integer> namedEntitiesFrecuencyReducedRDD = namedEntitiesFrecuencyRDD
                    .reduceByKey((a, b) -> a + b);

            // Crear el RDD de entidades nombradas con frecuencia reducida
            CreadorEntidades creador = new CreadorEntidades();
            JavaRDD<EntidadNombrada> namedEntitiesReducedRDD = namedEntitiesFrecuencyReducedRDD.map(
                    namedEntityFrecuency -> creador.createEntity(namedEntityFrecuency._1, namedEntityFrecuency._2));

            List<EntidadNombrada> namedEntitiesReduced = namedEntitiesReducedRDD.collect();

            // Cerrar el contexto de Spark
            sc.close();

            // Imprimir las entidades nombradas
            System.out.println(
                    "**********************************************************************************************");
            System.out.println("Named Entities: ");
            System.out.println(
                    "**********************************************************************************************");
            for (EntidadNombrada namedEntityReduced : namedEntitiesReduced) {
                namedEntityReduced.prettyPrint();
            }
            System.out.println(
                    "**********************************************************************************************");
            EntidadNombrada prettyPrint = new EntidadNombrada(null, null, 1, null);
            prettyPrint.reduceFrequency();
            prettyPrint.prettyPrintFrecuencias();

        } else if (args.length == 1 && args[0].equals("-search")) {
            // Retrieve the word or named entity to search for
            String searchTerm = "Chicago"; // args[1];

            // Create the RDD of articles
            JavaRDD<Article> articlesRDD = feedsRDD.flatMap(feed -> feed.getArticleList().iterator());

            // Create the RDD of (documentID, documentText) pairs
            JavaPairRDD<Long, Article> documentRDD = articlesRDD.zipWithIndex().mapToPair(
                    article -> new Tuple2<>(
                            article._2(), // Use the index as the document ID
                            article._1() // Use the article as the document text
                    ));

            // Create the inverted index RDD
            JavaPairRDD<String, Iterable<String>> invertedIndexRDD = documentRDD.flatMapToPair(document -> {
                List<Tuple2<String, String>> terms = new ArrayList<>();
                document._2.computeNamedEntities(new QuickHeuristic())
                        .forEach(namedEntity -> terms.add(new Tuple2<>(namedEntity, document._1.toString())));
                return terms.iterator();
            }).groupByKey();

            // Filter the documents containing the search term
            JavaPairRDD<String, Integer> documentsWithSearchTermRDD = invertedIndexRDD
                    .filter(term -> term._1.equals(searchTerm))
                    .flatMapToPair(term -> {
                        List<Tuple2<String, Integer>> documents = new ArrayList<>();
                        for (String documentID : term._2) {
                            documents.add(new Tuple2<>(documentID, 1));
                        }
                        return documents.iterator();
                    })
                    .reduceByKey((a, b) -> a + b);

            // Order the documents by the number of occurrences
            JavaPairRDD<Integer, String> orderedDocumentsRDD = documentsWithSearchTermRDD
                    .mapToPair(doc -> new Tuple2<>(doc._2, doc._1))
                    .sortByKey(false)
                    .mapToPair(doc -> new Tuple2<>(doc._1, doc._2));

            // Collect the ordered documents
            List<Tuple2<Integer, String>> orderedDocuments = orderedDocumentsRDD.collect();

            // Collect the articles of the ordered documents
            List<Tuple2<Article, Integer>> orderedArticles = new ArrayList<>();
            for (Tuple2<Integer, String> orderedDoc : orderedDocuments) {
                orderedArticles.add(
                        new Tuple2<>(documentRDD.filter(doc -> doc._1.equals(Long.parseLong(orderedDoc._2))).first()._2,
                                orderedDoc._1));
            }

            // Close the Spark context
            sc.close();

            System.out.println(
                    "**********************************************************************************************");
            System.out.println("Ordered articles: ");
            System.out.println(
                    "**********************************************************************************************");
            for (Tuple2<Integer, String> orderedDoc : orderedDocuments) {
                System.out.println(
                        "Articulo con " + orderedDoc._1 + " apariciones de la entidad nombrada " + searchTerm + ":");
                System.out.println(orderedArticles.get(orderedDocuments.indexOf(orderedDoc))._1.getTitle());
                System.out.println(
                        "**********************************************************************************************");
            }

        } else {
            printHelp();
        }
    }

}
