

package com.inHealth.server.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.shaded.org.apache.avro.SchemaBuilder;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import static org.apache.spark.sql.types.DataTypes.DoubleType;


@Repository
        public class BiometricRepository {

            private final String uri = "hdfs://34.237.242.179:9000";
            private final String hdfsDir = "/sensors-data";

            public JavaRDD<String>  uploadToHdfs(String content, String user, String fileName) throws IOException {
                Configuration conf = new Configuration();
                System.setProperty("HADOOP_USER_NAME", "root");
                conf.set("fs.defaultFS", uri);
                conf.setBoolean("dfs.client.use.datanode.hostname", true);

                FileSystem fs = FileSystem.get(conf);

                Path hdfswritepathuser =new Path( hdfsDir+"/" +user);

                System.out.println("Before create");

                if (!fs.exists(hdfswritepathuser)) {
                    System.out.println("Before created");

                    fs.mkdirs(hdfswritepathuser);
                    System.out.println(" create");

                }
                System.out.println("Create file");


                Path hdfswritepath = new Path( hdfsDir+"/" +user+"/" + fileName);
                FSDataOutputStream outputStream = fs.create(hdfswritepath);
                outputStream.writeBytes(content);
                outputStream.close();

                writeParquetToHdfs(content,  user,  fileName,  hdfsDir,  conf);
                System.out.println("Final file");

                SparkSession spark = SparkSession.builder()
                        .appName("Distance Calculation")
                        .master("local[*]")
                        .getOrCreate();
                // Load all files from the sensor-data directory into an RDD
                JavaRDD<String> dataRDD = spark.read()
                        .textFile("hdfs://34.237.242.179:9000/sensors-data/" + user + "/" + fileName)
                        .toJavaRDD();

                return dataRDD;

            }

    public void uploadToHbase(String content) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hbase");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        Connection conn = ConnectionFactory.createConnection(conf);
        System.out.println("Table 1");

        Admin admin = conn.getAdmin();
        System.out.println("Table 1");

        TableName tableName = TableName.valueOf("test");
        System.out.println("Table 1");


        System.out.println("Table 1");

        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
        System.out.println("Table 1");

        tableDescriptor.addFamily(new HColumnDescriptor("cf1"));
        tableDescriptor.addFamily(new HColumnDescriptor("cf2"));
        System.out.println("Table 1");

        admin.createTable(tableDescriptor);
        System.out.println("Table 1");

        admin.close();
        conn.close();
    }
    public void writeParquetToHdfs(String content, String user, String fileName, String hdfsDir, Configuration conf) throws IOException {

          String namef= fileName.replace(".txt","");
        SparkSession spark = SparkSession.builder()
                .appName("create-parquet")
                .master("local[*]")
                .getOrCreate();

        StructType schema = new StructType()
                .add("deviceId", DataTypes.StringType)
                .add("timestamp", DataTypes.StringType)
                .add("xAccelerometer", DoubleType)
                .add("yAccelerometer", DoubleType)
                .add("zAccelerometer", DoubleType)
                .add("xGyroscope", DoubleType)
                .add("yGyroscope", DoubleType)
                .add("zGyroscope", DoubleType);

        String url="hdfs://34.237.242.179:9000/sensors-data/"+user+"/"+fileName;
        Dataset<Row> data = spark.read()
                .option("header", "false")
                .option("delimiter", ",")
                .schema(schema)
                .csv(url);

        System.out.println("nameFile");
        System.out.println("hdfs://34.237.242.179:9000/sensors-data/"+user+"/"+namef + ".parquet");

        data.write().parquet("hdfs://34.237.242.179:9000/sensors-data/"+user+"/"+namef + ".parquet");
    }

        }