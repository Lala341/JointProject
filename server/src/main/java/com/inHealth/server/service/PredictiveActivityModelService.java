package com.inHealth.server.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.*;

import static org.apache.spark.sql.functions.monotonically_increasing_id;

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
    static public  JavaRDD<LabeledPoint>  preprocessDataSets( SparkSession spark, String urlx, String urly) {

        Dataset<String> columnsRDD = spark.read().textFile("hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/features.txt");
        String[] columnsList = (String[]) columnsRDD.collect();

        String url=urlx;
        Dataset<Row> data_x = spark.read()
                .option("header", "false")
                .option("delimiter", " ")
                .csv(url);

        url=urly;
        Dataset<Row> data_y = spark.read()
                .option("header", "false")
                .option("delimiter", " ")
                .csv(url);


        String[] columns = data_x.columns();

        for (int i = 0; i < columnsList.length; i++) {
            String oldColumnName = data_x.columns()[i+2];
            String newColumnName = columnsList[i];
            data_x = data_x.withColumnRenamed(oldColumnName, newColumnName);
        }



        Dataset<Row> selectedFeatures = data_x.select("1 tBodyAcc-mean()-X", "2 tBodyAcc-mean()-Y", "3 tBodyAcc-mean()-Z",
                "121 tBodyGyro-mean()-X","122 tBodyGyro-mean()-Y","123 tBodyGyro-mean()-Z");
        selectedFeatures.show(1);

        String oldColumnName = data_y.columns()[0];
        String newColumnName = "y";
        data_y = data_y.withColumnRenamed(oldColumnName, newColumnName);



        // Add unique IDs to each row of feature dataset
        Dataset<Row> featureDataWithId = selectedFeatures.withColumn("id", monotonically_increasing_id());

        // Add unique IDs to each row of label dataset
        Dataset<Row> labelDataWithId = data_y.withColumn("id", monotonically_increasing_id());

        // Join datasets by the generated IDs
        Dataset<Row> joinedData = featureDataWithId
                .join(labelDataWithId, "id")
                .drop("id");


        joinedData = joinedData.select(
                joinedData.col("y").cast("double"),
                joinedData.col("1 tBodyAcc-mean()-X").cast("double"),
                joinedData.col( "2 tBodyAcc-mean()-Y").cast("double"),
                joinedData.col("3 tBodyAcc-mean()-Z").cast("double"),
                joinedData.col("121 tBodyGyro-mean()-X").cast("double"),
                joinedData.col("122 tBodyGyro-mean()-Y").cast("double"),
                joinedData.col( "123 tBodyGyro-mean()-Z").cast("double")
        );
        // Show joined data

        // Convert dataset to JavaRDD<LabeledPoint>
        JavaRDD<LabeledPoint> labeledPoints = joinedData.toJavaRDD()
                .map(row -> {
                    // Extract label
                    double y = row.getDouble(0);

                    // Extract features
                    double[] featuresArray = new double[row.size() - 1];
                    for (int i = 1; i < row.size(); i++) {
                        Double value=0.0;

                        if(!row.isNullAt(i)){
                            value=row.getDouble(i);
                        }
                        featuresArray[i - 1] = value;
                    }

                    // Create vector of features
                    Vector featuresVector = Vectors.dense(featuresArray);

                    // Create LabeledPoint
                    return new LabeledPoint(y, featuresVector);
                });


        return labeledPoints;
    }
    static public  void createmodel() {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("InHealthSensors-PreprocessingDataset").master("local[*]").getOrCreate();

        JavaRDD<LabeledPoint> trainData = preprocessDataSets(  spark, "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/train/X_train.txt",
                "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/train/y_train.txt" );

        JavaRDD<LabeledPoint> testData = preprocessDataSets(  spark, "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/test/X_test.txt",
                "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/test/y_test.txt" );
        // Stop the SparkSession


        Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<>();
        int numClasses = 7;
        String impurity = "gini";
        int maxDepth = 9;
        int maxBins = 32;



// create model
        final DecisionTreeModel model = DecisionTree.trainClassifier(trainData, numClasses, categoricalFeaturesInfo, impurity, maxDepth, maxBins);

// Evaluate model on training instances and compute training error
        JavaPairRDD<Double, Double> predictionAndLabel = testData.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));

        Double testErrDT = 1.0 * predictionAndLabel.filter(pl -> !pl._1().equals(pl._2())).count() / testData.count();


        System.out.println("File testErrDT:");
        System.out.println(testErrDT);


        // specify the path where the model will be saved
        String modelPath = "hdfs://54.84.181.116:9000/models-datasets";

        // save the model
        model.save(spark.sparkContext(), modelPath);
        DecisionTreeModel dtModel = DecisionTreeModel.load(spark.sparkContext(), modelPath);


        System.out.println("Final complete");

        spark.stop();


    }
    //   .setMaster("spark://ec2-54-84-181-116.compute-1.amazonaws.com:7077");
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
    static public  void example() {


        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkConf conf = new SparkConf().setAppName("InHealthSensorsPPP").setMaster("local[*]");
        conf.set("spark.driver.bindAddress", "127.0.0.1");


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
    public static void main(String[] args) {
        createmodel();
    }
}