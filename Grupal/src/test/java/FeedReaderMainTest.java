import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaPairRDD;

import scala.Tuple2;
import src.FeedReaderMain;

public class FeedReaderMainTest {
    private JavaSparkContext sc;

    @Before
    public void setUp() {
        SparkConf conf = new SparkConf().setAppName("MiAplicacion").setMaster("local[*]");
        sc = new JavaSparkContext(conf);
    }

    @After
    public void tearDown() {
        sc.close();
    }

    @Test
    public void testDoOrder_ShouldReturnOrderedIDRDD() {
        // Arrange
        ArrayList<Tuple2<String, Long>> elementos = new ArrayList<>();
        elementos.add(new Tuple2<>("Test", 1L));
        elementos.add(new Tuple2<>("UwU", 1L));
        elementos.add(new Tuple2<>("Test", 1L));
        elementos.add(new Tuple2<>("Test", 1L));
        elementos.add(new Tuple2<>("NoTest", 3L));
        elementos.add(new Tuple2<>("NoTest", 3L));
        elementos.add(new Tuple2<>("NoTest", 4L));
        elementos.add(new Tuple2<>("Test", 4L));

        JavaPairRDD<String, Long> wordsRDD = sc.parallelizePairs(elementos);

        String searchTerm = "Test";

        // Act
        JavaPairRDD<Long, Long> orderedIDRDD = FeedReaderMain.doOrder(wordsRDD, searchTerm);

        List<Tuple2<Long, Long>> orderedID = orderedIDRDD.collect();

        // Assert
        assertEquals(2, orderedID.size());
        assertEquals(new Tuple2<>(1L, 3L), orderedID.get(0));
        assertEquals(new Tuple2<>(4L, 1L), orderedID.get(1));
    }
}
