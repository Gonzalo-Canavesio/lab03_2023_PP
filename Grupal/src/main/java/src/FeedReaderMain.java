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

// import org.knowm.xchart.*;

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

  public static JavaPairRDD<Long, Long> doOrder(JavaPairRDD<String, Long> wordsRDD, String searchTerm) {
    // Se filtran los pares para obtener solo los que contienen el termino buscado,
    // es decir, los que son de la forma (searchTerm, ID)
    JavaPairRDD<String, Long> wordsFilteredRDD = wordsRDD.filter(document -> document._1.equals(searchTerm));

    // Se convierten los pares de la forma (searchTerm, ID) a (ID, 1)
    JavaPairRDD<Long, Long> singleIDRDD = wordsFilteredRDD.mapToPair(document -> new Tuple2<>(document._2, 1L));

    // Se combinan los pares con la misma clave, es decir, si los pares son
    // (ID, 1) y (ID, 1) se convierten en (ID, 2)
    JavaPairRDD<Long, Long> countIDRDD = singleIDRDD.reduceByKey((a, b) -> a + b);

    // Se ordenan los documentos por el segundo elemento del par, es decir, por el
    // numero de veces que aparece el termino de busqueda en el documento
    JavaPairRDD<Long, Long> orderedIDRDD = countIDRDD.mapToPair(x -> x.swap()).sortByKey(false)
        .mapToPair(x -> x.swap());

    return orderedIDRDD;
  }

  // Codigo muerto, dejado como prueba de que al menos lo intentamos
  public static void doGraph(JavaPairRDD<String, Long> wordsRDD) {
    // Se convierten los pares de la forma (Palabra, ID) a (Palabra, 1)
    JavaPairRDD<String, Long> singleWordRDD = wordsRDD.mapToPair(document -> new Tuple2<>(document._1, 1L));

    // Se combinan los pares con la misma clave, es decir, si los pares son
    // (Palabra, 1) y (Palabra, 1) se convierten en (Palabra, 2)
    JavaPairRDD<String, Long> countWordRDD = singleWordRDD.reduceByKey((a, b) -> a + b);

    JavaPairRDD<String, Long> countWordRDD2 = countWordRDD.mapToPair(x -> x.swap()).sortByKey(false)
        .mapToPair(x -> x.swap());

    List<Tuple2<String, Long>> countWordRDD3 = countWordRDD2.take(10);

    List<String> words = new ArrayList<>();
    List<Long> counts = new ArrayList<>();

    for (Tuple2<String, Long> tuple : countWordRDD3) {
      words.add(tuple._1);
      counts.add(tuple._2);
    }

    // // Crear el gráfico de barras (No funciona)
    // CategoryChart chart =
    // new CategoryChartBuilder()
    // .width(800)
    // .height(600)
    // .title("Histograma de palabras")
    // .xAxisTitle("Palabras")
    // .yAxisTitle("Frecuencia")
    // .build();

    // // Agregar los datos al gráfico
    // chart.addSeries("Serie 1", words, counts);

    // // Mostrar el gráfico
    // new SwingWrapper<>(chart).displayChart();

  }
  private static void printNamedEntities(List<EntidadNombrada> namedEntitiesReduced) {
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
  }

  private static void printOrderedArticles(List<Tuple2<Article, Long>> orderedArticles, String searchTerm) {
      System.out.println(
              "**********************************************************************************************");
      System.out.println("Ordered articles: ");
      System.out.println(
              "**********************************************************************************************");
      for (Tuple2<Article, Long> orderedDoc : orderedArticles) {
          System.out.println(
                  "Articulo con " + orderedDoc._2 + " apariciones de la palabra " + searchTerm); System.out.println("Titulo: " + orderedDoc._1.getTitle());

          System.out.println("Link: " + orderedDoc._1.getLink());
          System.out.println(
                  "**********************************************************************************************");
      }
  }

  public static void main(String[] args) {
    System.out.println("************* FeedReader version 2.0 *************");

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
      JavaRDD<String> namedEntitiesRDD = articlesRDD.flatMap(
          article -> {
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

      printNamedEntities(namedEntitiesReduced);

    } else if (args.length == 2 && args[0].equals("-search")) {
      // Obtener todos los articulos de los feeds
      JavaRDD<Article> articlesRDD = feedsRDD.flatMap(feed -> feed.getArticleList().iterator());

      // Asignarle un indice a cada articulo
      // Cada articulo se transforma en un par de la forma (Articulo, ID)
      JavaPairRDD<Article, Long> articlesWithIDRDD = articlesRDD.zipWithUniqueId();

      // Se obtienen los pares de la forma (Palabra, ID) para cada palabra de
      // cada articulo
      JavaPairRDD<String, Long> wordsRDD = articlesWithIDRDD.flatMapToPair(
          document -> {
            List<Tuple2<String, Long>> terms = new ArrayList<>();
            for (String term : (document._1.getText() + " " + document._1.getTitle()).split(" ")) {
              terms.add(new Tuple2<>(term, document._2));
            }
            return terms.iterator();
          });

      // Realización de histograma de palabras para el punto estrella
      // Se establece el termino de busqueda a partir de los argumentos
      String searchTerm = args[1];

      JavaPairRDD<Long, Long> orderedIDRDD = doOrder(wordsRDD, searchTerm);

      // Se recogen los pares (Indice, Numero de apariciones) en una lista
      List<Tuple2<Long, Long>> orderedID = orderedIDRDD.collect();

      // Se recogen los pares (Articulo, Indice) en una lista
      List<Tuple2<Article, Long>> articles = articlesWithIDRDD.collect();

      // Cerrar el contexto de Spark
      sc.close();

      // Se crea una lista de pares (Articulo, Numero de apariciones) a partir de las
      // dos listas anteriores
      List<Tuple2<Article, Long>> orderedArticles = new ArrayList<>();
      for (Tuple2<Long, Long> ordered : orderedID) {
          for (Tuple2<Article, Long> article : articles) {
              if (ordered._1.equals(article._2)) {
                  orderedArticles.add(new Tuple2<>(article._1, ordered._2));
              }
          }
      }

      printOrderedArticles(orderedArticles, searchTerm);
    } else {
        printHelp();
    }
  }
}
