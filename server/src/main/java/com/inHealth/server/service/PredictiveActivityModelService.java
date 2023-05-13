package com.inHealth.server.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.inHealth.server.ServerApplication;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.springframework.boot.SpringApplication;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
public class PredictiveActivityModelService {

    static public  void createModel() {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        SparkConf conf = new SparkConf().setAppName("InHealthSensors").setMaster("spark://54.84.181.116:7077");
        conf.set("spark.kubernetes.driver.annotation.sidecar.istio.io/inject", "false");
        conf.set("spark.kubernetes.executor.annotation.sidecar.istio.io/inject", "false");

        System.out.println(conf);
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println(sc);


        JavaRDD<String> data = sc.textFile("hdfs://54.84.181.116:9000/sensors-data/1e9c1862-ec25-4cde-a689-38ab696ccba1/sensor-data_1e9c1862-ec25-4cde-a689-38ab696ccba1_2023-04-19T14-05-11.txt");

        // Print file content
        System.out.println("File content:");
        System.out.println(data);
        System.out.println("perro1");
        List<String> lines = data.take(1);

        for (String line : lines) {
            System.out.println("perro");
            System.out.println(line);
        }
        System.out.println("File content: c");

        // Close Spark context
        sc.close();


    }
    public static void noting() {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        SparkConf conf = new SparkConf().setAppName("InHealthSensors").setMaster("spark://54.84.181.116:7077");
        conf.set("spark.driver.log.level", "ERROR");
        conf.set("spark.executor.log.level", "ERROR");

        conf.set("spark.kubernetes.driver.annotation.sidecar.istio.io/inject", "false");
        conf.set("spark.driver.port", "8081");
        conf.set("spark.driver.maxResultSize", "4g");

        conf.set("spark.driver.memory", "4g");
        conf.set("spark.executor.memory", "4g");
        // Create Spark context
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("ppp1");

        // Create an RDD containing a single string
        JavaRDD<String> rdd = sc.parallelize(Arrays.asList("Hello, World!"));
        System.out.println("ppp2");

        // Print each element of the RDD
        for (String s : rdd.collect()) {
            System.out.println("ppp3");

            System.out.println(s);
        }
        System.out.println("ppp4");

        // Stop the Spark context
        sc.stop();
    }
    public static void main(String[] args) {
        noting();
    }
}