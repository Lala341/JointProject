package com.inHealth.server.dto;

import com.inHealth.server.model.graph.Answer;
import com.inHealth.server.model.graph.DietBehaviorQuestion;

import java.util.List;

public class DietBehaviorQuestionDTO {
    public DietBehaviorQuestion dietBehaviorQuestion;
    public List<Answer> answers;

    public DietBehaviorQuestionDTO(DietBehaviorQuestion dietBehaviorQuestion, List<Answer> answers) {
        this.dietBehaviorQuestion = dietBehaviorQuestion;
        this.answers = answers;
    }
}
