import parser.feedParser.RedditParser;
import parser.feedParser.RssParser;
import parser.subscriptionParser.JSONParser;

import subscription.*;
import httpRequest.*;
import namedEntity.CreadorEntidades;
import namedEntity.EntidadNombrada;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import feed.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;

import scala.Tuple2;

public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}

    private static Feed doParse(RoughFeed roughFeed) {
        if(roughFeed.getUrlType().equals("rss")){
            RssParser result = new RssParser();
			return result.parse(roughFeed);
        } else if (roughFeed.getUrlType().equals("reddit")){
            RedditParser result = new RedditParser();
			return result.parse(roughFeed);
        } else {
			System.out.println("Invalid feed type");
			return null;
		}
    }

	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {

			// // Leer el archivo de suscription por defecto y parsearlo
			// JSONParser subscriptionParser = new JSONParser();
			// Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

			// // Llamar al httpRequester para obtener el feed del servidor
			// httpRequester httpRequester = new httpRequester(subscription);
			// List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			// // Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds e imprimirlos
			// for(RoughFeed roughFeed : roughFeeds){
            //     Feed feed = doParse(roughFeed);
			// 	if(feed != null){
			// 		feed.prettyPrint();
			// 	}
			// }

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

			// Imprimir los feeds parseados
			feedsRDD.foreach(feed -> feed.prettyPrint());

			sc.close();

		} else if (args.length == 1 && args[0].equals("-ne")){

			// Leer el archivo de suscription por defecto y parsearlo
			JSONParser subscriptionParser = new JSONParser();
			Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);
			List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds
			// Heuristic heuristica = new QuickHeuristic(); // Si se quiere cambiar la heuristica, modificar esta linea
			// for(RoughFeed roughFeed : roughFeeds){
            //     Feed feed = doParse(roughFeed);
			// 	// Extraer las entidades nombradas solo de los feeds RSS porque el texto de los feeds de Reddit bugea la heuristica
			// 	if(feed != null && roughFeed.getUrlType().equals("rss")){
			// 		for(Article article : feed.getArticleList()){
			// 			// Llamar a la heuristica y extraer las entidades nombradas
			// 			article.computeNamedEntities(heuristica);
			// 		}
			// 	}
			// }
			// Adaptar el código anterior para que se ejecute en Spark

			// Crear el contexto de Spark
			SparkConf conf = new SparkConf().setAppName("FeedReader").setMaster("local");
			JavaSparkContext sc = new JavaSparkContext(conf);

			// Crear el RDD de feeds
			JavaRDD<RoughFeed> roughFeedsRDD = sc.parallelize(roughFeeds);

			// Crear el RDD de feeds parseados
			JavaRDD<Feed> feedsRDD = roughFeedsRDD.map(roughFeed -> doParse(roughFeed));

			// Crear el RDD de articulos
			JavaRDD<Article> articlesRDD = feedsRDD.flatMap(feed -> feed.getArticleList().iterator());

			// Crear el RDD de entidades nombradas
			Heuristic heuristica = new QuickHeuristic();
			JavaRDD<String> namedEntitiesRDD = articlesRDD.flatMap(article -> {
				if(article.getType() == "rss"){
					return article.computeNamedEntities(heuristica).iterator();
				} else {
					return new ArrayList<String>().iterator();
				}
			});

			// Crear el RDD de entidades nombradas con frecuencia
			JavaPairRDD<String, Integer> namedEntitiesFrecuencyRDD = namedEntitiesRDD.mapToPair(namedEntity -> new Tuple2<>(namedEntity, 1));
			JavaPairRDD<String, Integer> namedEntitiesFrecuencyReducedRDD = namedEntitiesFrecuencyRDD.reduceByKey((a, b) -> a + b);

			// Crear el RDD de entidades nombradas con frecuencia reducida
			CreadorEntidades creador = new CreadorEntidades();
			JavaRDD<EntidadNombrada> namedEntitiesReducedRDD = namedEntitiesFrecuencyReducedRDD.map(namedEntityFrecuency -> creador.createEntity(namedEntityFrecuency._1, namedEntityFrecuency._2));

			List<EntidadNombrada> namedEntitiesReduced = namedEntitiesReducedRDD.collect();

			// Imprimir las entidades nombradas
			System.out.println("**********************************************************************************************");
			System.out.println("Named Entities: ");
			System.out.println("**********************************************************************************************");
			for(EntidadNombrada namedEntityReduced : namedEntitiesReduced){
				namedEntityReduced.prettyPrint();
			}
			System.out.println("**********************************************************************************************");
			EntidadNombrada prettyPrint = new EntidadNombrada(null, null, 1, null);
			prettyPrint.reduceFrequency();
			prettyPrint.prettyPrintFrecuencias();

			// Cerrar el contexto de Spark
			sc.close();

		}else {
			printHelp();
		}
	}

}