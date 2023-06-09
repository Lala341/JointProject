package com.inHealth.server.service;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.model.StepsKPI;
import com.inHealth.server.repository.DistanceKPIRepository;
import com.inHealth.server.repository.StepsKPIRepository;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@EnableScheduling
public class DescriptiveAnalysisService {

    private final SparkSession spark;

    public DescriptiveAnalysisService() {
        spark = SparkSession.builder()
                .appName("Step Counting")
                .master("local[*]")
                .getOrCreate();
    }

    public double calculateDistance(String user,JavaRDD<String> dataRDD) {
        SparkSession spark = SparkSession.builder()
                .appName("Distance Calculation")
                .master("local[*]")
                .getOrCreate();

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
                    double distance = 0.5 * (Math.abs(x) + Math.abs(y) + Math.abs(z)) * Math.pow(timeDifference, 2);
                    totalDistance += distance;
                }
                previousTime = currentTime;
            }

            return Collections.singletonList(totalDistance).iterator();
        });

        // Calculate the total distance crossed using reduce action
        double totalDistance = distancesRDD.reduce(Double::sum);

        return totalDistance;
    }

    public int calculateTotalSteps(String user,  JavaRDD<String> dataRDD, double threshold, int windowSize, int stepSize) {
        SparkSession spark = SparkSession.builder()
                .appName("Step Counting")
                .master("local[*]")
                .getOrCreate();



        // Calculate the total steps using map transformation with windowing and averaging
        JavaRDD<Integer> stepsRDD = dataRDD.mapPartitions(lines -> {
            List<Double> magnitudeWindow = new ArrayList<>();
            int totalSteps = 0;

            while (lines.hasNext()) {
                String line = lines.next();
                String[] values = line.split(",");
                double x = Double.parseDouble(values[2]);
                double y = Double.parseDouble(values[3]);
                double z = Double.parseDouble(values[4]);
                double magnitude = Math.sqrt(x * x + y * y + z * z);

                magnitudeWindow.add(magnitude);

                // Check if the window is filled
                if (magnitudeWindow.size() >= windowSize) {
                    // Calculate the average magnitude within the window
                    double averageMagnitude = magnitudeWindow.stream()
                            .reduce(0.0, Double::sum) / magnitudeWindow.size();

                    // Detect step if the average magnitude crosses the threshold
                    if (averageMagnitude > threshold) {
                        totalSteps++;
                    }

                    // Slide the window by the specified step size
                    magnitudeWindow = magnitudeWindow.subList(stepSize, magnitudeWindow.size());
                }
            }

            return Collections.singletonList(totalSteps).iterator();

        });

        // Calculate the total steps taken using reduce action
        int totalSteps = stepsRDD.reduce(Integer::sum);

        return totalSteps;
    }

    @PreDestroy
    private void cleanup() {
        spark.stop();
    }
}
