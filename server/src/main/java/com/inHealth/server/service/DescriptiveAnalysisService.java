package com.inHealth.server.service;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

public class DescriptiveAnalysisService {
    static public double calculateDistance(String user) {
        SparkSession spark = SparkSession.builder()
                .appName("Distance Calculation")
                .master("local[*]")
                .getOrCreate();

        //JavaSparkContext sparkContext = new JavaSparkContext("local[*]", "Distance Calculation");

        // Load your data into an RDD or DataFrame
        JavaRDD<String> dataRDD = spark.sparkContext()
                .textFile("hdfs://54.84.181.116:9000/sensors-data/"+user+"/sensor-data_1e9c1862-ec25-4cde-a689-38ab696ccba1_2023-04-19T14-05-06.txt", 1)
                .toJavaRDD();

        // Calculate the distance using map transformation
        JavaRDD<Double> distancesRDD = dataRDD.mapPartitions(lines -> {
            double previousTime = 0;
            double totalDistance = 0;
            while (lines.hasNext()) {
                String line = lines.next();
                String[] values = line.split(",");
                double x = Double.parseDouble(values[2]);
                double y = Double.parseDouble(values[3]);
                double z = Double.parseDouble(values[4]);
                String timestampStr = values[1];
                Instant timestamp = Instant.parse(timestampStr);
                double currentTime = timestamp.toEpochMilli() / 1000.0;
                if (previousTime != 0) {
                    double timeDifference = currentTime - previousTime;
                    // Calculate distance using the formula: distance = 0.5 * acceleration * time^2
                    double distance = 0.5 * (x + y + z) * Math.pow(timeDifference, 2);
                    totalDistance += distance;
                }
                previousTime = currentTime;
            }

            return Collections.singletonList(totalDistance).iterator();
        });

        // Calculate the total distance crossed using reduce action
        double totalDistance = distancesRDD.reduce(Double::sum);

        System.out.println("Total Distance Crossed: " + totalDistance + " m");

        // Stop SparkSession
        spark.stop();
        return totalDistance;
    }
    public static void main(String[] args) {
        System.out.println(calculateDistance("1e9c1862-ec25-4cde-a689-38ab696ccba1"));
    }
}
