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

        JavaRDD<Tuple8<String,String,Double,Double,Double,Double,Double,Double>> data = dataRDD.map(line-> {
            String[] parts = line.split(",");

            if (parts.length < 8) {
                return new Tuple8<>("", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            }
            else {
            String id = parts[0];
            String timestamp = parts[1];
            String date = timestamp.split("T")[0];
            double x_accelerometer = Double.parseDouble(parts[2]);
            double y_accelerometer = Double.parseDouble(parts[3]);
            double z_accelerometer = Double.parseDouble(parts[4]);
            double x_gyroscope = Double.parseDouble(parts[5]);
            double y_gyroscope = Double.parseDouble(parts[6]);
            double z_gyroscope = Double.parseDouble(parts[7]);
            return new Tuple8<>(id, date, x_accelerometer, y_accelerometer, z_accelerometer, x_gyroscope, y_gyroscope, z_gyroscope);
            }
        });

        // Group by user and date, calculate the sum and count of x, y, and z coordinates
        JavaPairRDD<Tuple2<String, String>, Tuple7<Double, Double, Double, Double, Double, Double, Integer>> groupedData = data
                .mapToPair(tuple -> new Tuple2<>(new Tuple2<>(tuple._1(), tuple._2()),
                        new Tuple7<>(tuple._3(), tuple._4(), tuple._5(), tuple._6(), tuple._7(), tuple._8(), 1)))
                .reduceByKey((t1, t2) -> new Tuple7<>(t1._1() + t2._1(), t1._2() + t2._2(), t1._3() + t2._3(), t1._4() + t2._4(), t1._5() + t2._5(), t1._6() + t2._6(), t1._7() + t2._7()))
                .mapValues(tuple -> new Tuple7<>(tuple._1() / tuple._7(), tuple._2() / tuple._7(), tuple._3() / tuple._7(), tuple._4() / tuple._7(), tuple._5() / tuple._7(), tuple._6() / tuple._7(), tuple._7()));

        // Iterate over the resulting groupedData RDD
        List<Tuple2<Tuple2<String, String>, Tuple7<Double, Double, Double, Double, Double, Double, Integer>>> result = groupedData.collect();

        for (Tuple2<Tuple2<String, String>, Tuple7<Double, Double, Double, Double, Double, Double, Integer>> tuple : result) {
            Tuple2<String, String> key = tuple._1();
            Tuple7<Double, Double, Double, Double, Double, Double, Integer> value = tuple._2();

            // Create a new Statistics object and add it to the list
            Statistics statistics = new Statistics(null, key._1(), key._2(), value._1(), value._2(), value._3(), value._4(), value._5(), value._6());

            // Print the statistics (optional)
            System.out.println(
                    "Statistics: \n Id: " + statistics.getUserId()
                    + "\n Date:" + statistics.getDate()
                    + "\n Average accelerometer x:" + statistics.getAvgAccX()
                    + "\n Average accelerometer y:" + statistics.getAvgAccY()
                    + "\n Average accelerometer z:" + statistics.getAvgAccZ()
                    + "\n Average gyroscope x:" + statistics.getAvgGyroX()
                    + "\n Average gyroscope y:" + statistics.getAvgGyroY()
                    + "\n Average gyroscope z:" + statistics.getAvgGyroZ()
                    + "\n ______________________");

            statisticsRepository.save(statistics);
        }
    }

    @PreDestroy
    private void cleanup() {
        spark.stop();
    }
}
