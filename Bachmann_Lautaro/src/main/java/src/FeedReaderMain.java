import parser.feedParser.RedditParser;
import parser.feedParser.RssParser;
import parser.subscriptionParser.JSONParser;
import subscription.*;
import httpRequest.*;
import namedEntity.EntidadNombrada;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import feed.*;
import java.util.List;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
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
        // Leer el archivo de suscription por defecto y parsearlo
        JSONParser subscriptionParser = new JSONParser();
        Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

        // Llamar al httpRequester para obtener el feed del servidor
        httpRequester httpRequester = new httpRequester(subscription);
        List<RoughFeed> roughFeeds = httpRequester.getFeeds();

        // inicializar spark
        SparkConf conf = new SparkConf().setAppName("EntityParser").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // crear RDD de feeds
        JavaRDD<RoughFeed> roughFeedsRDD = sc.parallelize(roughFeeds);

		if (args.length == 0) {
            JavaRDD<Feed> parsedFeeds = roughFeedsRDD
                .map(feed -> doParse(feed))
                .filter(feed -> feed != null);
            parsedFeeds.foreach(feed -> feed.prettyPrint());

		} else if (args.length == 1 && args[0].equals("-ne")){

			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds
			Heuristic heuristica = new QuickHeuristic(); // Si se quiere cambiar la heuristica, modificar esta linea
            JavaRDD<Article> articles = roughFeedsRDD
                .filter(roughFeed -> roughFeed.getUrlType().equals("rss"))
                .map(feed -> doParse(feed))
                .filter(feed -> feed != null)
                .flatMap(feed -> feed.getArticleList().iterator());

            JavaRDD<String> entidadesNombradas = articles.flatMap(
                    article -> article.computeNamedEntities(heuristica).iterator()
                );

			// Imprimir las entidades nombradas
            final JavaPairRDD<String, Integer> entityFrequency = entidadesNombradas.mapToPair(entity -> new Tuple2<>(entity, 1))
                .reduceByKey((i1, i2) -> i1 + i2);

			// Article articuloVacio = new Article(null, null, null, null);
			// articuloVacio.prettyPrintNamedEntities();
			EntidadNombrada prettyPrint = new EntidadNombrada(null, null, 1, null);
			prettyPrint.reduceFrequency();
			prettyPrint.prettyPrintFrecuencias();
		}else {
			printHelp();
		}
	}

}
