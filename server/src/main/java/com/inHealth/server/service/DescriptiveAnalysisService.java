package com.inHealth.server.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class DescriptiveAnalysisService {
    static public double calculateDistance(String user, LocalDate date) {
        SparkSession spark = SparkSession.builder()
                .appName("Distance Calculation")
                .master("local[*]")
                .getOrCreate();

        // Load all files from the user directory with the specific date into an RDD
        JavaRDD<String> dataRDD = spark.read()
                .textFile("hdfs://54.84.181.116:9000/sensors-data/" + user + "/sensor-data*.txt")
                .toJavaRDD()
                .filter(line -> {
                    String[] values = line.split(",");
                    String timestampStr = values[1];
                    LocalDate fileDate = LocalDate.parse(timestampStr.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE);
                    return fileDate.equals(date);
                });

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

        // Stop SparkSession
        spark.stop();
        return totalDistance;
    }
    public static void main(String[] args) {
        var totalDistance = calculateDistance("1e9c1862-ec25-4cde-a689-38ab696ccba1", LocalDate.of(2023, 4, 19));
        System.out.println("Total Distance Crossed: " + totalDistance + " m");
    }
}
