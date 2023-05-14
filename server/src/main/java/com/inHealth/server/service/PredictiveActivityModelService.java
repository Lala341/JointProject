package com.inHealth.server.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PredictiveActivityModelService {

    private static final int NUM_SAMPLES = 1;

    static public  void preprocessDataBiometrics() {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("DatasetExample").master("local[*]").getOrCreate();

        StructType schema = new StructType()
                .add("deviceId", DataTypes.StringType)
                .add("timestamp", DataTypes.StringType)
                .add("xAccelerometer", DataTypes.DoubleType)
                .add("yAccelerometer", DataTypes.DoubleType)
                .add("zAccelerometer", DataTypes.DoubleType)
                .add("xGyroscope", DataTypes.DoubleType)
                .add("yGyroscope", DataTypes.DoubleType)
                .add("zGyroscope", DataTypes.DoubleType);

        String url="hdfs://54.84.181.116:9000/sensors-data/1e9c1862-ec25-4cde-a689-38ab696ccba1/sensor-data_1e9c1862-ec25-4cde-a689-38ab696ccba1_2023-04-19T14-05-11.txt";
        Dataset<Row> data = spark.read()
                .option("header", "false")
                .option("delimiter", ",")
                .schema(schema)
                .csv(url);

        // Show the contents of the dataset
        data.show(1);

        // Stop the SparkSession
        spark.stop();


    }
    static public  void example() {

        StructType schema = new StructType()
                .add("deviceId", DataTypes.StringType)
                .add("timestamp", DataTypes.StringType)
                .add("xAccelerometer", DataTypes.DoubleType)
                .add("yAccelerometer", DataTypes.DoubleType)
                .add("zAccelerometer", DataTypes.DoubleType)
                .add("xGyroscope", DataTypes.DoubleType)
                .add("yGyroscope", DataTypes.DoubleType)
                .add("zGyroscope", DataTypes.DoubleType);




        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkConf conf = new SparkConf().setAppName("InHealthSensors").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("ERROR");

        JavaRDD<String> data = sc.textFile("hdfs://54.84.181.116:9000/sensors-data/1e9c1862-ec25-4cde-a689-38ab696ccba1/sensor-data_1e9c1862-ec25-4cde-a689-38ab696ccba1_2023-04-19T14-05-11.txt");

        // Print file content
        System.out.println("File content:");
        System.out.println(data);
        List<String> lines = data.take(1);

        for (String line : lines) {
            System.out.println(line);
        }

        // Close Spark context
        sc.close();


    }
    public static void example2() {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        SparkConf conf = new SparkConf().setAppName("InHealthSensorsPPP").setMaster("local[*]");
        conf.set("spark.driver.bindAddress", "127.0.0.1");
             //   .setMaster("spark://ec2-54-84-181-116.compute-1.amazonaws.com:7077");
                ;
        //  conf.set("spark.driver.log.level", "ERROR");
        // conf.set("spark.executor.log.level", "ERROR");

        // conf.set("spark.kubernetes.driver.annotation.sidecar.istio.io/inject", "false");

          //  conf.set("spark.local.ip","54.84.181.116") ;
          //    conf.set("spark.driver.host","54.84.181.116");
          //  conf.set("spark.driver.host","localhost");
          // conf.set("spark.driver.port", "7077");
          //  conf.set("spark.driver.bindAddress", "54.84.181.116");

         //   conf.set("spark.driver.host", "54.84.181.116");
        //  conf.set("spark.driver.maxResultSize", "4g");

        //  conf.set("spark.driver.memory", "4g");
        // conf.set("spark.executor.memory", "4g");
        // Create Spark context
        JavaSparkContext sc = new JavaSparkContext(conf);
        System.out.println("ppp1");

        List<Integer> l = new ArrayList<>(NUM_SAMPLES);
        for (int i = 0; i < NUM_SAMPLES; i++) {
            l.add(i);
        }

        long count = sc.parallelize(l).filter(i -> {
            double x = Math.random();
            double y = Math.random();
            return x*x + y*y < 1;
        }).count();
        System.out.println("Pi is roughly " + 4.0 * count / NUM_SAMPLES);
        System.out.println("ppp2");
        System.out.println("ppp4");

        // Stop the Spark context
        sc.stop();
    }
    public static void main(String[] args) {
        preprocessDataBiometrics();
    }
}