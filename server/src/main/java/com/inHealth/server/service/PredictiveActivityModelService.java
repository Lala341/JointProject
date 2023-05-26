package com.inHealth.server.service;

import com.inHealth.server.model.ModelMetrics;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.RandomForest;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.time.LocalDate;
import java.util.*;

import static java.lang.Math.min;
import static org.apache.spark.sql.functions.monotonically_increasing_id;
import static org.apache.spark.sql.types.DataTypes.DoubleType;

public class PredictiveActivityModelService {

    private static final int NUM_SAMPLES = 1;

    static public  void preprocessDataBiometrics() {
        System.setProperty("HADOOP_CONF_DIR",  "/models-datasets");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("DatasetExampleIn").master("local[*]").getOrCreate();

        StructType schema = new StructType()
                .add("deviceId", DataTypes.StringType)
                .add("timestamp", DataTypes.StringType)
                .add("xAccelerometer", DoubleType)
                .add("yAccelerometer", DoubleType)
                .add("zAccelerometer", DoubleType)
                .add("xGyroscope", DoubleType)
                .add("yGyroscope", DoubleType)
                .add("zGyroscope", DoubleType);

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

    static public  JavaRDD<LabeledPoint>  preprocessDataSets(SparkSession spark, String urlx, String urly) {

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


                    org.apache.spark.mllib.linalg.Vector featuresVector = Vectors.dense(featuresArray);

                    // Create LabeledPoint
                    return new LabeledPoint(y, featuresVector);
                });


        return labeledPoints;
    }

    public static double predictLabel(Row row, DecisionTreeModel model) {
        // Extract features
        double[] featuresArray = new double[row.size()];
        for (int i = 0; i < row.size(); i++) {
            Double value = 0.0;

            if (!row.isNullAt(i)) {
                value = row.getDouble(i);
            }
            featuresArray[i] = value;
        }

        // Create vector of features

        org.apache.spark.mllib.linalg.Vector featuresVector = Vectors.dense(featuresArray);

        // Predict the label using the model
        double prediction = model.predict(featuresVector);

        return prediction;
    }


    public static double predictLabelRandom(Row row, RandomForestModel model) {
        // Extract features
        double[] featuresArray = new double[row.size()];
        for (int i = 0; i < row.size(); i++) {
            Double value = 0.0;

            if (!row.isNullAt(i)) {
                value = row.getDouble(i);
            }
            featuresArray[i] = value;
        }

        // Create vector of features

        org.apache.spark.mllib.linalg.Vector  featuresVector = Vectors.dense(featuresArray);

        // Predict the label using the model
        double prediction = model.predict(featuresVector);

        return prediction;
    }

    public static  String getLabel(double prediction){
        String label;
        switch ((int) prediction) {
            case 1:
                label = "WALKING";
                break;
            case 2:
                label = "WALKING_UPSTAIRS";
                break;
            case 3:
                label = "WALKING_DOWNSTAIRS";
                break;
            case 4:
                label = "SITTING";
                break;
            case 5:
                label = "STANDING";
                break;
            case 6:
                label = "LAYING";
                break;
            default:
                label = "UNKNOWN";
        }

        return label;
    }


    public static void preHdfs(String hdfsDir)  {
        String uri = "hdfs://54.84.181.116:9000";

        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        conf.set("fs.defaultFS", uri);
        conf.setBoolean("dfs.client.use.datanode.hostname", true);

        System.out.println("Start verify folder");


        try {
            FileSystem fs = FileSystem.get(conf);
            Path hdfswritepathuser1 =new Path( hdfsDir+"/data");
            Path hdfswritepathuser2 =new Path( hdfsDir+"/metadata");

            System.out.println("Before create");
            if (fs.exists(hdfswritepathuser1)) {
                System.out.println("exist");

                fs.delete(hdfswritepathuser1, true);
                fs.delete(hdfswritepathuser2, true);
                System.out.println(" delete");

            }
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e.toString());

        }


        System.out.println("Final file");

    }
    static public  void createmodelsandtrain() {
        System.setProperty("HADOOP_CONF_DIR",  "/models-datasets");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("InHealthSensors-PreprocessingDatasetInFi").config("spark.hadoop.validateOutputSpecs", "false").master("local[*]").getOrCreate();

        System.out.println("Final clearCache");

        JavaRDD<LabeledPoint> trainData = preprocessDataSets(  spark, "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/train/X_train.txt",
                "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/train/y_train.txt" );

        JavaRDD<LabeledPoint> testData = preprocessDataSets(  spark, "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/test/X_test.txt",
                "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/test/y_test.txt" );
        // Stop the SparkSession


        createmodelandtrain_decisiontree(spark, trainData, testData);
        createmodelandtrain_randomforest(spark, trainData, testData);

        System.out.println("Final complete");

        spark.stop();


    }

    static public  void calculatemetrics_testdecisiontree(SparkSession spark, String version, String type, JavaRDD<LabeledPoint> testData ,
                                                          ModelMetricsService modelService, LocalDate date) {

        String modelPath = "hdfs://54.84.181.116:9000/models-datasets/tree";


        DecisionTreeModel model = DecisionTreeModel.load( spark.sparkContext(),modelPath);


// Evaluate model on training instances and compute training error
        JavaPairRDD<Double, Double> predictionAndLabel = testData.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));

        Double testErrDT = 1.0 * predictionAndLabel.filter(pl -> !pl._1().equals(pl._2())).count() / testData.count();


        // Create RDD for prediction and label pairs
        JavaRDD<Tuple2<Object, Object>> predictionAndLabel1 = testData.map(p -> new Tuple2<>(model.predict(p.features()), p.label()));

// Instantiate MulticlassMetrics object
        MulticlassMetrics metrics = new MulticlassMetrics(predictionAndLabel1.rdd());



// Accuracy
        double accuracy = metrics.accuracy();

        for (var i=1; i<7;i++){

            // Precision
            double precision = metrics.precision(i);

// F1 score
            double f1Score = metrics.fMeasure(i);

// Recall
            double recall = metrics.recall(i);

            ModelMetrics modelme= new ModelMetrics(null, type,  version, testErrDT, accuracy, precision, recall, f1Score,date, getLabel(i),  i);
            modelService.save(modelme);
            System.out.println(modelme.toString());
        }



    }

    static public  void calculatemetrics_testrandom(SparkSession spark, String version, String type, JavaRDD<LabeledPoint> testData ,
                                                          ModelMetricsService modelService, LocalDate date) {

        String modelPath = "hdfs://54.84.181.116:9000/models-datasets/random";


        RandomForestModel model = RandomForestModel.load(spark.sparkContext(),modelPath);


// Evaluate model on training instances and compute training error
        JavaPairRDD<Double, Double> predictionAndLabel = testData.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));

        Double testErrDT = 1.0 * predictionAndLabel.filter(pl -> !pl._1().equals(pl._2())).count() / testData.count();


        // Create RDD for prediction and label pairs
        JavaRDD<Tuple2<Object, Object>> predictionAndLabel1 = testData.map(p -> new Tuple2<>(model.predict(p.features()), p.label()));

// Instantiate MulticlassMetrics object
        MulticlassMetrics metrics = new MulticlassMetrics(predictionAndLabel1.rdd());



// Accuracy
        double accuracy = metrics.accuracy();

        for (var i=1; i<7;i++){

            // Precision
            double precision = metrics.precision(i);

// F1 score
            double f1Score = metrics.fMeasure(i);

// Recall
            double recall = metrics.recall(i);

            ModelMetrics modelme= new ModelMetrics(null, type,  version, testErrDT, accuracy, precision, recall, f1Score,date, getLabel(i),  i);
            modelService.save(modelme);
            System.out.println(modelme.toString());
        }



    }

    static public  void calculatemetrics_test(String version) {
        System.setProperty("HADOOP_CONF_DIR",  "/models-datasets");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("InHealthSensors-PreprocessingDatasetInFi").config("spark.hadoop.validateOutputSpecs", "false").master("local[*]").getOrCreate();



        JavaRDD<LabeledPoint> testData = preprocessDataSets(  spark, "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/test/X_test.txt",
                "hdfs://54.84.181.116:9000/models-datasets/human_sensorsUCI HAR Dataset/test/y_test.txt" );
        // Stop the SparkSession

        ModelMetricsService modelService= new ModelMetricsService();
        LocalDate date = LocalDate.now();


        calculatemetrics_testdecisiontree(spark,version, "DecisionTree", testData, modelService, date);
        calculatemetrics_testrandom(spark,version, "DecisionTree", testData, modelService, date);


        System.out.println("Final complete metrics");
        spark.stop();


    }


    static public  void createmodelandtrain_decisiontree(SparkSession spark,JavaRDD<LabeledPoint> trainData,JavaRDD<LabeledPoint> testData )  {

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
        String modelPath = "hdfs://54.84.181.116:9000/models-datasets/tree";

        // save the model
        preHdfs(modelPath);
        model.save(spark.sparkContext(), modelPath);
        DecisionTreeModel dtModel = DecisionTreeModel.load(spark.sparkContext(), modelPath);


        System.out.println("Final complete");




    }
    static public  void createmodelandtrain_randomforest(SparkSession spark,JavaRDD<LabeledPoint> trainData,JavaRDD<LabeledPoint> testData)  {
        Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<>();
        int numClasses = 7;
        int numTrees = 100;
        String featureSubsetStrategy = "auto";
        String impurity = "gini";
        int maxDepth = 9;
        int maxBins = 32;

        // Create random forest model
        final RandomForestModel model = RandomForest.trainClassifier(trainData, numClasses,
                categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins,1);


// Evaluate model on training instances and compute training error
        JavaPairRDD<Double, Double> predictionAndLabel = testData.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));

        Double testErrDT = 1.0 * predictionAndLabel.filter(pl -> !pl._1().equals(pl._2())).count() / testData.count();


        System.out.println("File testErrDT:");
        System.out.println(testErrDT);


        // specify the path where the model will be saved
        String modelPath = "hdfs://54.84.181.116:9000/models-datasets/random";

        preHdfs(modelPath);
        // save the model
        model.save(spark.sparkContext(), modelPath);


        System.out.println("Final complete");

    }


    static public  String testmodel_decisiontree( double meanax,double meanay, double meanaz, double meangx, double meangy, double meangz) {
        System.setProperty("HADOOP_CONF_DIR",  "/models-datasets");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("InHealthSensors-PreprocessingDatasetInF").master("local[*]").getOrCreate();

        String modelPath = "hdfs://54.84.181.116:9000/models-datasets/tree";


        DecisionTreeModel loadedModel = DecisionTreeModel.load( spark.sparkContext(),modelPath);

        StructType schema = new StructType()
                .add("1 tBodyAcc-mean()-X", DoubleType, false)
                .add("2 tBodyAcc-mean()-Y", DoubleType, false)
                .add("3 tBodyAcc-mean()-Z", DoubleType, false)
                .add("121 tBodyGyro-mean()-X", DoubleType, false)
                .add("122 tBodyGyro-mean()-Y", DoubleType, false)
                .add("123 tBodyGyro-mean()-Z", DoubleType, false);

// Create the Row object with values
        Row rowToPredict = RowFactory.create( meanax, meanay,  meanaz,  meangx,  meangy,  meangz); // Replace with your own values


// Predict the label for the row
        double predictedLabel = predictLabel(rowToPredict, loadedModel);

        spark.stop();

        return getLabel(predictedLabel);

    }






    static public  String testmodel_randomforest( double meanax,double meanay, double meanaz, double meangx, double meangy, double meangz) {
        System.setProperty("HADOOP_CONF_DIR",  "/sensors-data");
        System.setProperty("HADOOP_USER_NAME", "root");

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        SparkSession spark = SparkSession.builder().appName("InHealthSensors-PreprocessingDatasetInF").master("local[*]").getOrCreate();

        String modelPath = "hdfs://54.84.181.116:9000/models-datasets/random";


        RandomForestModel loadedModel = RandomForestModel.load(spark.sparkContext(),modelPath);

        StructType schema = new StructType()
                .add("1 tBodyAcc-mean()-X", DoubleType, false)
                .add("2 tBodyAcc-mean()-Y", DoubleType, false)
                .add("3 tBodyAcc-mean()-Z", DoubleType, false)
                .add("121 tBodyGyro-mean()-X", DoubleType, false)
                .add("122 tBodyGyro-mean()-Y", DoubleType, false)
                .add("123 tBodyGyro-mean()-Z", DoubleType, false);

// Create the Row object with values
        Row rowToPredict = RowFactory.create( meanax, meanay,  meanaz,  meangx,  meangy,  meangz); // Replace with your own values

// Predict the label for the row
        double predictedLabel = predictLabelRandom(rowToPredict, loadedModel);

        spark.stop();

        return getLabel(predictedLabel);


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


        System.setProperty("HADOOP_CONF_DIR",  "/models-datasets");
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
        System.out.println("start");
        // createmodelsandtrain();

        calculatemetrics_test("1.0.0");

       // String predictedLabel=testmodel_decisiontree(1,2,3,4,5,6);
       // String predictedLabel1=testmodel_randomforest(1,2,3,4,5,6);

       // System.out.println("Predicted label Tree: " + predictedLabel);
       // System.out.println("Predicted label Random: " + predictedLabel1);

    }
}