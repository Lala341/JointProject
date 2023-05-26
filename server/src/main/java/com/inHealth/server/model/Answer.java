package com.inHealth.server.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Answer {
    @Id
    private String id;
    private String questionId;
    private String optionId;

    public Answer() {
        this.id = UUID.randomUUID().toString();
    }

    public Answer(String id, String questionId, String optionId) {
        this.id = id;
        this.questionId = questionId;
        this.optionId = optionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }
}
