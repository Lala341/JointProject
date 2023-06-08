package com.inHealth.server.dto;

import com.inHealth.server.model.graph.Answer;
import com.inHealth.server.model.graph.HealthQuestion;

import java.util.List;

public class HealthQuestionDTO {
    public HealthQuestion healthQuestion;
    public List<Answer> answers;

    public HealthQuestionDTO(HealthQuestion healthQuestion, List<Answer> answers) {
        this.healthQuestion = healthQuestion;
        this.answers = answers;
    }

    public HealthQuestionDTO() {
    }
}
