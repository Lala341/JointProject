package com.inHealth.server.service;

import com.inHealth.server.model.graph.DietBehaviorQuestion;
import com.inHealth.server.model.graph.HabitQuestion;
import com.inHealth.server.model.graph.HealthQuestion;
import com.inHealth.server.repository.graph.DietBehaviourQuestionRepository;
import com.inHealth.server.repository.graph.HabitQuestionRepository;
import com.inHealth.server.repository.graph.HealthQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private HealthQuestionRepository healthQuestionRepository;

    @Autowired
    private HabitQuestionRepository habitQuestionRepository;

    @Autowired
    private DietBehaviourQuestionRepository dietBehaviourQuestionRepository;

    public List<HealthQuestion> getAllHealthQuestions() {
        List<HealthQuestion> questions = healthQuestionRepository.findAll();
        return questions;
    }

    public List<HabitQuestion> getAllHabitQuestions() {
        List<HabitQuestion> questions = habitQuestionRepository.findAll();
        return questions;
    }

    public List<DietBehaviorQuestion> getAllDietBehaviorQuestions() {
        List<DietBehaviorQuestion> questions = dietBehaviourQuestionRepository.findAll();
        return questions;
    }
}
