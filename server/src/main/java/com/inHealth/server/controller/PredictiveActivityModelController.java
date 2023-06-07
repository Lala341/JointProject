package com.inHealth.server.controller;

import com.inHealth.server.service.PredictiveActivityModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class PredictiveActivityModelController {
    @Autowired
    private PredictiveActivityModelService predictiveService;

    @CrossOrigin
    @GetMapping("/preprocessSaveDatasets")
    public ResponseEntity<String> datasets() {

        System.out.println("start");
        predictiveService.createfolderhdfs("hdfs://34.237.242.179:9000/models-datasets/datasets");
        predictiveService.createfolderhdfs("hdfs://34.237.242.179:9000/models-datasets/datasets/train");
        predictiveService.createfolderhdfs("hdfs://34.237.242.179:9000/models-datasets/datasets/test");
        predictiveService.preprocessSaveDataModels();

        return new ResponseEntity<>("Successful creation of files.", HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/createmodelsandtrain")
    public ResponseEntity<String> createmodel() {

        System.out.println("start");
        predictiveService.createmodelsandtrain();

        return new ResponseEntity<>("Successful creation of model.", HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/calculatemetrics")
    public ResponseEntity<String> calculatemetrics() {

        System.out.println("start");
        predictiveService.calculatemetrics_test("1.0.0");

        //  String predictedLabel=testmodel_decisiontree(0.25717778,-0.02328523,-0.014653762,0.89847935,0.0,0.95018164);
        //  String predictedLabel1=testmodel_decisiontree(0.11453319731152992,0.7540551762857872,0.17677547053059106,-0.36085168527211237,-0.18468181612511084,-0.21062950750740914);
        //  String predictedLabel3=testmodel_decisiontree(0.11453319731152992,-0.7540551762857872,-0.17677547053059106,-0.36085168527211237,-0.18468181612511084,-0.21062950750740914);
        //  String predictedLabel2=testmodel_decisiontree(1,2,3,4,5,6);

        //   System.out.println("Predicted label Tree: " + predictedLabel);
        //    System.out.println("Predicted label Random: " + predictedLabel1);
        //    System.out.println("Predicted label Random: " + predictedLabel2);
        //    System.out.println("Predicted label Random: " + predictedLabel3);

        return new ResponseEntity<>("Successful creation of model metrics.", HttpStatus.OK);
    }
}