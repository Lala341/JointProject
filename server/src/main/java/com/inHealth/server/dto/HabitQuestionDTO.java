package com.inHealth.server.dto;

import com.inHealth.server.model.graph.Answer;
import com.inHealth.server.model.graph.HabitQuestion;
import com.inHealth.server.model.graph.HealthQuestion;

import java.util.List;

public class HabitQuestionDTO {
    public HabitQuestion habitQuestion;
    public List<Answer> answers;

    public HabitQuestionDTO(HabitQuestion habitQuestion, List<Answer> answers) {
        this.habitQuestion = habitQuestion;
        this.answers = answers;
    }
}
