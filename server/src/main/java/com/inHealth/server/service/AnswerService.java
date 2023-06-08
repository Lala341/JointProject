package com.inHealth.server.service;

import com.inHealth.server.model.graph.Answer;
import com.inHealth.server.model.graph.HabitQuestion;
import com.inHealth.server.model.graph.HealthQuestion;
import com.inHealth.server.repository.graph.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> findByHealthQuestion(HealthQuestion question) {
        return answerRepository.findByHealthQuestion(question.getId());
    }

    public List<Answer> findByHabitQuestion(HabitQuestion question) {
        return answerRepository.findByHabitQuestion(question.getId());
    }
}
