

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
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;

@Repository
        public class BiometricRepository {

            private final String uri = "hdfs://namenode:9000";
            private final String hdfsDir = "/sensorData/t.txt";
            public void uploadToHdfs(String content) throws IOException {
                Configuration conf = new Configuration();
                System.setProperty("HADOOP_USER_NAME", "root");
                FileSystem fs = FileSystem.get(URI.create(uri), conf);
                //TODO: Change file name once everything is working
                String fileName = "test.txt";
                Path hdfswritepath = new Path( "/" + fileName);
                FSDataOutputStream outputStream = fs.create(hdfswritepath);
                outputStream.writeBytes(content);
                outputStream.close();
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

        }