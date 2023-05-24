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
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
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
			// Leer el archivo de suscription por defecto y parsearlo 
			JSONParser subscriptionParser = new JSONParser();
			Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);

			List<RoughFeed> roughFeeds = httpRequester.getFeeds();


			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds e imprimirlos (Usando Spark)
			SparkConf conf = new SparkConf().setAppName("FeedReader").setMaster("local");
			JavaSparkContext sc = new JavaSparkContext(conf);
			JavaRDD<RoughFeed> roughFeedsRDD = sc.parallelize(roughFeeds);
			JavaRDD<Feed> feedsRDD = roughFeedsRDD.map(roughFeed -> doParse(roughFeed)); // Parseo de los feeds usando map de manera paralela/distribuida
			List<Feed> feeds = feedsRDD.collect(); // Recolecto los feeds en una lista
			sc.close();

			feeds.forEach(feed -> { if(feed != null) feed.prettyPrint(); });

		} else if (args.length == 1 && args[0].equals("-ne")){

			// Leer el archivo de suscription por defecto y parsearlo
			JSONParser subscriptionParser = new JSONParser();
			Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);
			List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds
			SparkConf conf = new SparkConf().setAppName("FeedReader").setMaster("local");
			JavaSparkContext sc = new JavaSparkContext(conf);
			JavaRDD<RoughFeed> roughFeedsRDD = sc.parallelize(roughFeeds);

			Heuristic heuristica = new QuickHeuristic(); // Si se quiere cambiar la heuristica, modificar esta linea

			// Parseo de los feeds y obtengo las entidades nombradas a partir de ese feed, usando flatMap de manera paralela/distribuida
			JavaRDD<String> wordsRDD = roughFeedsRDD.flatMap(roughFeed -> {
				Feed feed = doParse(roughFeed);
				List<String> words = new ArrayList<String>();
				if(feed != null && roughFeed.getUrlType().equals("rss")) {
					for(Article article : feed.getArticleList()) {
						words.addAll(article.computeNamedEntities(heuristica));
					}
				}
				return words.iterator();
			});

			JavaPairRDD<String, Integer> pairs = wordsRDD.mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((x, y) -> (int) x + (int) y); // Cuento las ocurrencias de cada palabra

			List<Tuple2<String, Integer>> output = pairs.collect(); // Recolecto los resultados en una lista

			sc.close();

			// Creo las entidades nombradas a partir de la lista de tuplas obtenida en el paso anterior
			List<EntidadNombrada> namedEntityList = new ArrayList<EntidadNombrada>();
			CreadorEntidades creador = new CreadorEntidades();
			for(Tuple2<String, Integer> tuple : output) {
				EntidadNombrada entidad = creador.createEntity(tuple._1, tuple._2);
				namedEntityList.add(entidad);
			}

			// PrettyPrint de las entidades nombradas
			for(EntidadNombrada entidad : namedEntityList) {
				entidad.prettyPrint();
			}

			// PrettyPrint de las frecuencias
			EntidadNombrada prettyPrint = new EntidadNombrada(null, null, 1, null);
			prettyPrint.reduceFrequency();
			prettyPrint.prettyPrintFrecuencias();
		}else {
			printHelp();
		}
	}

}
