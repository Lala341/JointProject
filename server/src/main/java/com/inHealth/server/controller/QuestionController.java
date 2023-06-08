package com.inHealth.server.controller;

import com.inHealth.server.dto.DietBehaviorQuestionDTO;
import com.inHealth.server.dto.HabitQuestionDTO;
import com.inHealth.server.dto.HealthQuestionDTO;
import com.inHealth.server.model.graph.DietBehaviorQuestion;
import com.inHealth.server.model.graph.HabitQuestion;
import com.inHealth.server.model.graph.HealthQuestion;
import com.inHealth.server.service.AnswerService;
import com.inHealth.server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/health")
    public ResponseEntity<List<HealthQuestionDTO>> getAllHealthQuestions() {
        List<HealthQuestion> questions = questionService.getAllHealthQuestions();
        List<HealthQuestionDTO> dtos = new ArrayList<>();
        for (HealthQuestion question : questions) {
            HealthQuestionDTO dto = new HealthQuestionDTO(question, answerService.findByHealthQuestion(question));
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/habit")
    public ResponseEntity<List<HabitQuestionDTO>> getAllHabitQuestions() {
        List<HabitQuestion> questions = questionService.getAllHabitQuestions();
        List<HabitQuestionDTO> dtos = new ArrayList<>();
        for (HabitQuestion question : questions) {
            HabitQuestionDTO dto = new HabitQuestionDTO(question, answerService.findByHabitQuestion(question));
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/diet")
    public ResponseEntity<List<DietBehaviorQuestionDTO>> getAllDietBehaviorQuestions() {
        List<DietBehaviorQuestion> questions = questionService.getAllDietBehaviorQuestions();
        List<DietBehaviorQuestionDTO> dtos = new ArrayList<>();
        for (DietBehaviorQuestion question : questions) {
            DietBehaviorQuestionDTO dto = new DietBehaviorQuestionDTO(question, answerService.findByDietBehaviorQuestion(question));
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
