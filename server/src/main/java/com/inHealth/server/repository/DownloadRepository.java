

package com.inHealth.server.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;

import static java.lang.Math.min;

@Repository
        public class DownloadRepository {

    private final String uri = "hdfs://namenode:9000";
    private final String hdfsDir = "/sensors-data";

    public void uploadToHdfs(String zipUrl, String hdfsPath) throws IOException {


        // Download the zip file from the URL
        URL url = new URL(zipUrl);
        InputStream inputStream = url.openStream();

        // Create a ZipInputStream to read the contents of the zip file
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        // Initialize HDFS configuration and file system object
        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        Path hdfswritepathuser = new Path(hdfsPath );

        System.out.println("Before create");
        if (!fs.exists(hdfswritepathuser)) {
            System.out.println("Before created");

            fs.mkdirs(hdfswritepathuser);
            System.out.println(" create");

        }
        System.out.println("Create file");


        // Loop through each entry in the zip file and extract its contents
        ZipEntry entry = null;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            String fileName = entry.getName();

            // Write the file to HDFS
            Path hdfsFilePath = new Path(hdfsPath + fileName);
            FSDataOutputStream outputStream = fs.create(hdfsFilePath);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = zipInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            zipInputStream.closeEntry();
        }

        // Close the ZipInputStream and input stream
        zipInputStream.close();
        inputStream.close();





    }
}