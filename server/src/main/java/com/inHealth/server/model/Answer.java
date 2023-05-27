package com.inHealth.server.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Answer {
    @Id
    private String id;
    private String questionId;
    private String answer;

    public Answer() {
        this.id = UUID.randomUUID().toString();
    }

    public Answer(String id, String questionId, String answer) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
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

    public String getanswer() {
        return answer;
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }
}
