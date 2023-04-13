package com.inHealth.server.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;


@Service
public class BiometricService {

    private String uri = "hdfs://54.235.57.170:9000";
    private String hdfsDir = "/tmp/hadoop-root/dfs/name";
    private Configuration conf = new Configuration();

    public void uploadToHdfs(String content) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataOutputStream outputStream = fs.create(new Path(hdfsDir + "/file.txt"));
        outputStream.writeBytes(content);
        outputStream.close();
    }
}
