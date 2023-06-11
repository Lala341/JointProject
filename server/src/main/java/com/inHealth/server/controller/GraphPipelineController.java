package com.inHealth.server.controller;

import com.inHealth.server.service.GraphPipelineService;
import com.inHealth.server.service.PredictionPipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphPipelineController {

        @Autowired
        private GraphPipelineService graphService;


    @Autowired
    private PredictionPipelineService graphpredService;

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


    @CrossOrigin
    @GetMapping("/getPrediction")
    public ResponseEntity<List> prediction(@RequestParam("person") String personId) {
        System.out.println("start");

        return new ResponseEntity<>(graphpredService.getPredicitonEnd(personId), HttpStatus.OK);
    }
}
