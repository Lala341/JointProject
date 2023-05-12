package com.inHealth.server.controller;

import com.inHealth.server.service.BiometricService;
import com.inHealth.server.service.DownloadService;
import com.inHealth.server.service.PredictiveActivityModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class BiometricController {

    private BiometricService biometricService;

    private DownloadService downloadService;


    private PredictiveActivityModelService modelService;


    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {

        try {
            String content = new String(file.getBytes());
            biometricService.uploadToHdfs(content, name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/datasets")
    public ResponseEntity<String> handleFileUploadDatasets(@RequestParam("url") String url, @RequestParam("name") String dir) {

        try {
           System.out.println(url);
            downloadService.uploadToHdfs(url, dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
                    
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<String> ping() {

        return new ResponseEntity<>("Web Server InHealth", HttpStatus.OK);
    }
}
