package com.inHealth.server.service;

import com.inHealth.server.repository.BiometricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Date;


@Service
public class BiometricService {

    @Autowired
    private BiometricRepository biometricRepository;

    public void uploadToHdfs(String content, String name) throws IOException {
        biometricRepository.uploadToHdfs(content, name);
    }
}
