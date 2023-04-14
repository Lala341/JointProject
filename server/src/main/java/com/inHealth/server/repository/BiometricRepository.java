package com.inHealth.server.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;

@Repository
public class BiometricRepository {

    private final String uri = "hdfs://54.235.57.170:9000";
    private final String hdfsDir = "/sensorData";
    public void uploadToHdfs(String content) throws IOException {
        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        //TODO: Change file name once everything is working
        String fileName = "test.txt";
        Path hdfswritepath = new Path(hdfsDir + "/" + fileName);
        FSDataOutputStream outputStream = fs.create(hdfswritepath);
        outputStream.writeBytes(content);
        outputStream.close();
    }
}
