package com.inHealth.server.service;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.model.StepsKPI;
import com.inHealth.server.repository.DistanceKPIRepository;
import com.inHealth.server.repository.StepsKPIRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.shaded.org.checkerframework.checker.units.qual.A;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@EnableScheduling
public class DescriptiveAnalysisService {

    private final SparkSession spark;
    @Autowired
    private DistanceKPIRepository distanceKPIRepository;
    @Autowired
    private StepsKPIRepository stepsKPIRepository;

    public DescriptiveAnalysisService() {
        spark = SparkSession.builder()
                .appName("Step Counting")
                .master("local[*]")
                .getOrCreate();
    }

    public double calculateDistance(String user, LocalDateTime date) {
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
                    return fileDate.equals(date.toLocalDate());
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
                    double distance = 0.5 * (Math.abs(x) + Math.abs(y) + Math.abs(z)) * Math.pow(timeDifference, 2);
                    totalDistance += distance;
                }
                previousTime = currentTime;
            }

            return Collections.singletonList(totalDistance).iterator();
        });

        // Calculate the total distance crossed using reduce action
        double totalDistance = distancesRDD.reduce(Double::sum);

        // Store the calculated KPI in MongoDB
        DistanceKPI distanceKPI = new DistanceKPI(user, date, totalDistance);
        distanceKPIRepository.save(distanceKPI);

        return totalDistance;
    }

    public int calculateTotalSteps(String user, LocalDateTime date, double threshold, int windowSize, int stepSize) {
        SparkSession spark = SparkSession.builder()
                .appName("Step Counting")
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
                    return fileDate.equals(date.toLocalDate());
                });

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

        // Store the calculated KPI in MongoDB
        StepsKPI stepsKPI = new StepsKPI(user, date, totalSteps);
        stepsKPIRepository.save(stepsKPI);

        return totalSteps;
    }

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void performAnalysisScheduled() {
        String uri = "hdfs://54.84.181.116:9000";
        String hdfsDir = "/sensors-data";

        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        conf.set("fs.defaultFS", uri);
        conf.setBoolean("dfs.client.use.datanode.hostname", true);
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            FileStatus[] userDirectories = fileSystem.listStatus(new Path(hdfsDir));

            for (FileStatus userDirectory : userDirectories) {
                String user = userDirectory.getPath().getName();
                LocalDateTime now = LocalDateTime.now();

                double distance = calculateDistance(user, now);
                System.out.println("Distance for user " + user + ": " + distance);

                int steps = calculateTotalSteps(user, now, 0.5, 10, 5);
                System.out.println("Total steps for user " + user + ": " + steps);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void cleanup() {
        spark.stop();
    }
}
