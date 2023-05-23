package com.inHealth.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("surveys")
public class Survey {
    @Id
    private String id;
    private String title;
    private String description;
    private List<Question> questions;
    private LocalDate date;

    public Survey() {
    }

    public Survey(String id, String title, String description, List<Question> questions, LocalDate date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
