package com.inHealth.server.model.graph;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("Answer")
public class Answer implements Serializable {
    @Id
    private String id;
    private String answer;
    private String date;

    private String text;

    @Relationship(type = "ANSWERED_HE", direction = Relationship.Direction.INCOMING)
    private HealthQuestion healthQuestion;

    @Relationship(type = "ANSWERED_D", direction = Relationship.Direction.INCOMING)
    private DietBehaviorQuestion dietQuestion;

    @Relationship(type = "ANSWERED_HA", direction = Relationship.Direction.INCOMING)
    private HabitQuestion habitQuestion;

    // Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HealthQuestion getHealthQuestion() {
        return healthQuestion;
    }

    public void setHealthQuestion(HealthQuestion healthQuestion) {
        this.healthQuestion = healthQuestion;
    }

    public DietBehaviorQuestion getDietQuestion() {
        return dietQuestion;
    }

    public void setDietQuestion(DietBehaviorQuestion dietQuestion) {
        this.dietQuestion = dietQuestion;
    }

    public HabitQuestion getHabitQuestion() {
        return habitQuestion;
    }

    public void setHabitQuestion(HabitQuestion habitQuestion) {
        this.habitQuestion = habitQuestion;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Answer() {
    }

    public Answer(String id, String answer, String date, String text, HabitQuestion habitQuestion) {
        this.id = id;
        this.answer = answer;
        this.date = date;
        this.text = text;
        this.habitQuestion = habitQuestion;
    }

    public Answer(String id, String answer, String date,  String text, HealthQuestion healthQuestion) {
        this.id = id;
        this.answer = answer;
        this.date = date;
        this.text = text;
        this.healthQuestion = healthQuestion;
    }

    public Answer(String id, String answer, String date,  String text, DietBehaviorQuestion dietQuestion) {
        this.id = id;
        this.answer = answer;
        this.date = date;
        this.text = text;
        this.dietQuestion = dietQuestion;
    }

    public Answer(String id, String answer, String date) {
        this.id = id;
        this.answer = answer;
        this.date = date;
    }
}

