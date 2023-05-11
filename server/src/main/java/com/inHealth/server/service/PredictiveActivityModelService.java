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
        SparkConf conf = new SparkConf().setAppName("App Name").setMaster("spark://localhost:7077");
        System.out.println(conf);
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println(sc);

        JavaRDD<String> data = sc.textFile("hdfs://namenode:9000/sensors-data/1e9c1862-ec25-4cde-a689-38ab696ccbc0/sensor-data_1e9c1862-ec25-4cde-a689-38ab696ccbc0_2023-04-19T13-41-44.txt");
        System.out.println(data);


    }
    public static void main(String[] args) {
        createModel();
    }
}