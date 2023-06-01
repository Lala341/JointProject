package com.inHealth.server.service;

import com.inHealth.server.model.Statistics;
import com.inHealth.server.repository.StatisticsRepository;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.*;

import javax.annotation.PreDestroy;
import java.lang.Double;
import java.time.LocalDateTime;
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

    public List<Statistics> calculateStatistics(String user, String fileName) {
        SparkSession spark = SparkSession.builder()
                .appName("Statistical Analysis")
                .master("local[*]")
                .getOrCreate();

        // Load all files from the sensor-data directory into an RDD
        JavaRDD<String> dataRDD = spark.read()
                .textFile("hdfs://34.237.242.179:9000/sensors-data/" + user + "/" + fileName)
                .toJavaRDD();

        JavaRDD<Tuple8<String,LocalDateTime,Double,Double,Double,Double,Double,Double>> data = dataRDD.map(line-> {
            String[] parts = line.split(",");

            if (parts.length < 8) {
                return new Tuple8<>("", null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            }
            else {
            String id = parts[0];
            String timestamp = parts[1];
            String dateStr = timestamp.split(":")[0] + ":" + timestamp.split(":")[1] + ":00";
            LocalDateTime date = LocalDateTime.parse(dateStr);
            double x_accelerometer = Double.parseDouble(parts[2]);
            double y_accelerometer = Double.parseDouble(parts[3]);
            double z_accelerometer = Double.parseDouble(parts[4]);
            double x_gyroscope = Double.parseDouble(parts[5]);
            double y_gyroscope = Double.parseDouble(parts[6]);
            double z_gyroscope = Double.parseDouble(parts[7]);
            return new Tuple8<>(id, date, x_accelerometer, y_accelerometer, z_accelerometer, x_gyroscope, y_gyroscope, z_gyroscope);
            }
        });


        JavaPairRDD<Tuple2<String, LocalDateTime>, Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer>> groupedData = data
                .mapToPair(tuple -> {
                    Tuple2<String, LocalDateTime> key = new Tuple2<>(tuple._1(), tuple._2());
                    Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer> value = new Tuple13<>(
                            tuple._3(), tuple._4(), tuple._5(), tuple._6(), tuple._7(), tuple._8(),
                            tuple._3(), tuple._4(), tuple._5(), tuple._6(), tuple._7(), tuple._8(), 1);
                    return new Tuple2<>(key, value);
                })
                .reduceByKey((Function2<Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer>, Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer>, Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer>>)(t1, t2) -> {
                    double sumAccX = t1._1() + t2._1();
                    double sumAccY = t1._2() + t2._2();
                    double sumAccZ = t1._3() + t2._3();
                    double sumGyroX = t1._4() + t2._4();
                    double sumGyroY = t1._5() + t2._5();
                    double sumGyroZ = t1._6() + t2._6();
                    double sumSqAccX = t1._7() + t2._7();
                    double sumSqAccY = t1._8() + t2._8();
                    double sumSqAccZ = t1._9() + t2._9();
                    double sumSqGyroX = t1._10() + t2._10();
                    double sumSqGyroY = t1._11() + t2._11();
                    double sumSqGyroZ = t1._12() + t2._12();
                    int count = t1._13() + t2._13();
                    return new Tuple13<>(sumAccX, sumAccY, sumAccZ, sumGyroX, sumGyroY, sumGyroZ,
                            sumSqAccX, sumSqAccY, sumSqAccZ, sumSqGyroX, sumSqGyroY, sumSqGyroZ, count);
                })
                .mapValues(tuple -> {
                    double avgXAcc = tuple._1() / tuple._13();
                    double avgYAcc = tuple._2() / tuple._13();
                    double avgZAcc = tuple._3() / tuple._13();
                    double avgXGyro = tuple._4() / tuple._13();
                    double avgYGyro = tuple._5() / tuple._13();
                    double avgZGyro = tuple._6() / tuple._13();
                    double varianceXAcc = (tuple._7() / tuple._13()) - (avgXAcc * avgXAcc);
                    double varianceYAcc = (tuple._8() / tuple._13()) - (avgYAcc * avgYAcc);
                    double varianceZAcc = (tuple._9() / tuple._13()) - (avgZAcc * avgZAcc);
                    double varianceXGyro = (tuple._10() / tuple._13()) - (avgXGyro * avgXGyro);
                    double varianceYGyro = (tuple._11() / tuple._13()) - (avgYGyro * avgYGyro);
                    double varianceZGyro = (tuple._12() / tuple._13()) - (avgZGyro * avgZGyro);
                    double stdDevXAcc = Math.sqrt(varianceXAcc);
                    double stdDevYAcc = Math.sqrt(varianceYAcc);
                    double stdDevZAcc = Math.sqrt(varianceZAcc);
                    double stdDevXGyro = Math.sqrt(varianceXGyro);
                    double stdDevYGyro = Math.sqrt(varianceYGyro);
                    double stdDevZGyro = Math.sqrt(varianceZGyro);
                    return new Tuple13<>(avgXAcc, avgYAcc, avgZAcc, avgXGyro, avgYGyro, avgZGyro,
                            stdDevXAcc, stdDevYAcc, stdDevZAcc, stdDevXGyro, stdDevYGyro, stdDevZGyro, tuple._13());
                });

        List<Tuple2<Tuple2<String, LocalDateTime>, Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer>>> result = groupedData.collect();
        List<Statistics> statisticsList = new ArrayList<>();

        for (Tuple2<Tuple2<String, LocalDateTime>, Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer>> tuple : result) {
            Tuple2<String, LocalDateTime> key = tuple._1();
            Tuple13<Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double, Integer> value = tuple._2();

            // Create a new Statistics object and add it to the list
            PredictiveActivityModelService service=new PredictiveActivityModelService();
            String predictActivityM1=service.predictmodel_decisiontree(  spark , value._1(), value._2(), value._3(), value._4(), value._5(), value._6());
            String predictActivityM2=service.predictmodel_randomforest( spark ,value._1(), value._2(), value._3(), value._4(), value._5(), value._6());

            Statistics statistics = new Statistics(null, key._1(), key._2(), value._1(), value._2(), value._3(), value._4(), value._5(), value._6(), value._7(), value._8(), value._9(), value._10(), value._11(), value._12(),predictActivityM1,predictActivityM2);

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
                    + "\n Standard deviation accelerometer x:" + statistics.getStdDevAccX()
                    + "\n Standard deviation accelerometer y:" + statistics.getStdDevAccY()
                    + "\n Standard deviation accelerometer z:" + statistics.getStdDevAccZ()
                    + "\n Standard deviation gyroscope x:" + statistics.getStdDevGyroX()
                    + "\n Standard deviation gyroscope y:" + statistics.getStdDevGyroY()
                    + "\n Standard deviation gyroscope z:" + statistics.getStdDevGyroZ()
                    + "\n predictActivityM1:" + statistics.getPredictActivityM1()
                    + "\n predictActivityM2:" + statistics.getPredictActivityM2()

                    + "\n ______________________");

            statisticsList.add(statistics);
        }
        return statisticsList;
    }

    @PreDestroy
    private void cleanup() {
        spark.stop();
    }
}
