package com.inHealth.server.service;

import com.inHealth.server.repository.BiometricRepository;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Date;


@Service
public class BiometricService {

    @Autowired
    private BiometricRepository biometricRepository;

    public void uploadToHdfs(String content) throws IOException {
        biometricRepository.uploadToHdfs(content);
    }
}