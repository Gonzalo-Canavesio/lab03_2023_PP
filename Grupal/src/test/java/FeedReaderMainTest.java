import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;

import scala.Tuple2;
import src.FeedReaderMain;

public class FeedReaderMainTest {
   @Test 
    public void testParte3() {
        FeedReaderMain f = new FeedReaderMain();
        SparkConf conf = new SparkConf().setAppName("MiAplicacion").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        
        ArrayList<Tuple2<String, Long>> elementos = new ArrayList<>();
        String searchTerm = "Test";

        elementos.add(new Tuple2<>("Test", 1L));
        elementos.add(new Tuple2<>("UwU", 1L));
        elementos.add(new Tuple2<>("Test", 1L));
        elementos.add(new Tuple2<>("Test", 1L));
        elementos.add(new Tuple2<>("NoTest", 3L));
        elementos.add(new Tuple2<>("NoTest", 3L));
        elementos.add(new Tuple2<>("NoTest", 4L));
        elementos.add(new Tuple2<>("Test", 4L));

        JavaPairRDD<String, Long> wordsRDD = sc.parallelizePairs(elementos);



        JavaPairRDD<Long, Long> orderedIDRDD = FeedReaderMain.doOrder(wordsRDD, searchTerm);

        List<Tuple2<Long, Long>> orderedID = orderedIDRDD.collect();

        assert(orderedID.get(0)._1 == 1L);
        assert(orderedID.get(0)._2 == 3L);
        assert(orderedID.get(1)._1 == 4L);
        assert(orderedID.get(1)._2 == 1L);
    } 
}
