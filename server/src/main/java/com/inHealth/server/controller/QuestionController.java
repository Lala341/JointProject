package com.inHealth.server.controller;

import com.inHealth.server.dto.DietBehaviorQuestionDTO;
import com.inHealth.server.dto.HabitQuestionDTO;
import com.inHealth.server.dto.HealthQuestionDTO;
import com.inHealth.server.model.graph.*;
import com.inHealth.server.service.AnswerService;
import com.inHealth.server.service.PersonService;
import com.inHealth.server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PersonService personService;

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

    @PostMapping("/answers")
    public ResponseEntity<Void> saveAnswers(@RequestBody List<Answer> answers, @RequestParam("person") String personId) {
        Person person = personService.findById(personId);
        if (person == null) {
            person = new Person();
            person.setId(personId);
        }
        for(Answer answer : answers) {
            person.addAnswer(answer);
        }
        System.out.println(personId);
        System.out.println(person);
        System.out.println(person.getId());
        person.setDemographics(null);
        person.setBodyMeasures(null);
        personService.save(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
