package com.inHealth.server.service;

import com.inHealth.server.model.graph.HealthQuestion;
import com.inHealth.server.repository.graph.HealthQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private HealthQuestionRepository healthQuestionRepository;

    public List<HealthQuestion> getAllHealthQuestions() {
        List<HealthQuestion> questions = healthQuestionRepository.findAll();
        return questions;
    }
}
