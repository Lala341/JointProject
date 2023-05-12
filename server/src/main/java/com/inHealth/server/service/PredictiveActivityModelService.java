package com.inHealth.server.service;

import java.io.IOException;

import com.inHealth.server.ServerApplication;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.springframework.boot.SpringApplication;

public class PredictiveActivityModelService {

    static public  void createModel() {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        SparkConf conf = new SparkConf().setAppName("InHealthSensors").setMaster("spark://54.84.181.116:7077")
                .set("spark.hadoop.fs.defaultFS", "hdfs://54.84.181.116:9000")
                .set("spark.hadoop.fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        System.out.println(conf);
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println(sc);


        JavaRDD<String> data = sc.textFile("hdfs://54.84.181.116:9000/sensors-data/1e9c1862-ec25-4cde-a689-38ab696ccba1/sensor-data_1e9c1862-ec25-4cde-a689-38ab696ccba1_2023-04-19T14-05-06.txt");

        // Print file content
        System.out.println("File content:");
        for (String line : data.collect()) {
            System.out.println(line);
        }
        System.out.println("File content: c");

        // Close Spark context
        sc.close();


    }
    public static void main(String[] args) {
        createModel();
    }
}