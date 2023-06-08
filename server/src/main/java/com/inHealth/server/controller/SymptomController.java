package com.inHealth.server.controller;

import com.inHealth.server.model.graph.ExperiencesSymptom;
import com.inHealth.server.model.graph.Symptom;
import com.inHealth.server.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptom")
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @GetMapping
    public ResponseEntity<List<Symptom>> getAll() {
        return new ResponseEntity<>(symptomService.findAll(), HttpStatus.OK);
    }

}
