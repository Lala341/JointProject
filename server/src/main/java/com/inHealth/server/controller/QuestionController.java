package com.inHealth.server.controller;

import com.inHealth.server.model.graph.HealthQuestion;
import com.inHealth.server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<HealthQuestion>> getAllHealthQuestions() {
        List<HealthQuestion> questions = questionService.getAllHealthQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
