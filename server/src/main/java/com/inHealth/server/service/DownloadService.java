package com.inHealth.server.service;

import com.inHealth.server.repository.BiometricRepository;
import com.inHealth.server.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class DownloadService {

    @Autowired
    private DownloadRepository downloadRepository;

    public void uploadToHdfs(String url, String name) throws IOException {
        downloadRepository.uploadToHdfs(url, name);
    }
}
