package com.inHealth.server.service;

import com.inHealth.server.model.Statistics;
import com.inHealth.server.repository.StatisticsRepository;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.*;

import javax.annotation.PreDestroy;
import java.lang.Double;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    private final SparkSession spark;
    @Autowired
    private StatisticsRepository statisticsRepository;

    public StatisticsService() {
        spark = SparkSession.builder()
                .appName("Statistics")
                .master("local[*]")
                .getOrCreate();
    }

    public void calculateStatistics() {
        SparkSession spark = SparkSession.builder()
                .appName("Statistical Analysis")
                .master("local[*]")
                .getOrCreate();

        // Load all files from the sensor-data directory into an RDD
        JavaRDD<String> dataRDD = spark.read()
                .textFile("hdfs://54.84.181.116:9000/sensors-data/*/sensor-data*.txt")
                .toJavaRDD();

        JavaRDD<Tuple5<String,String,Double,Double,Double>> data = dataRDD.map(line-> {
            String[] parts = line.split(",");
            String id = parts[0];
            String timestamp = parts[1];
            String date = timestamp.split("T")[0];
            double x_accelerometer = Double.parseDouble(parts[2]);
            double y_accelerometer = Double.parseDouble(parts[3]);
            double z_accelerometer = Double.parseDouble(parts[4]);
            return new Tuple5<>(id, date, x_accelerometer, y_accelerometer, z_accelerometer);
        });

        // Group by user and date, calculate the sum and count of x, y, and z coordinates
        JavaPairRDD<Tuple2<String, String>, Tuple4<Double, Double, Double, Integer>> groupedData = data
                .mapToPair(tuple -> new Tuple2<>(new Tuple2<>(tuple._1(), tuple._2()),
                        new Tuple4<>(tuple._3(), tuple._4(), tuple._5(), 1)))
                .reduceByKey((t1, t2) -> new Tuple4<>(t1._1() + t2._1(), t1._2() + t2._2(), t1._3() + t2._3(), t1._4() + t2._4()))
                .mapValues(tuple -> new Tuple4<>(tuple._1() / tuple._4(), tuple._2() / tuple._4(), tuple._3() / tuple._4(), tuple._4()));

        // Iterate over the resulting groupedData RDD
        List<Tuple2<Tuple2<String, String>, Tuple4<Double, Double, Double, Integer>>> result = groupedData.collect();

        for (Tuple2<Tuple2<String, String>, Tuple4<Double, Double, Double, Integer>> tuple : result) {
            Tuple2<String, String> key = tuple._1();
            Tuple4<Double, Double, Double, Integer> value = tuple._2();

            // Create a new Statistics object and add it to the list
            Statistics statistics = new Statistics(null, key._1(), key._2(), value._1(), value._2(), value._3());

            // Print the statistics (optional)
            System.out.println(
                    "Statistics: \n Id: " + statistics.getUserId()
                    + "\n Date:" + statistics.getDate()
                    + "\n Average x:" + statistics.getAvgAccX()
                    + "\n Average y:" + statistics.getAvgAccY()
                    + "\n Average z:" + statistics.getAvgAccZ()
                    + "\n ______________________");

            statisticsRepository.save(statistics);
        }
    }

    @PreDestroy
    private void cleanup() {
        spark.stop();
    }
}
