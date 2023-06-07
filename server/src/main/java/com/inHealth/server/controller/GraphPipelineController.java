package com.inHealth.server.controller;

import com.inHealth.server.service.GraphPipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graph")
public class GraphPipelineController {

        @Autowired
        private GraphPipelineService graphService;

        @CrossOrigin
        @GetMapping("/downloadDatasets")
        public ResponseEntity<String> datasets() {
            System.out.println("start");
            graphService.dowloadFilestoHdfs();
            return new ResponseEntity<>("Successful down of files.", HttpStatus.OK);
        }


    @CrossOrigin
    @GetMapping("/loadDatasets")
    public ResponseEntity<String> loaddatasets() {
        System.out.println("start");
        graphService.loadData();
        return new ResponseEntity<>("Successful load", HttpStatus.OK);
    }
}
