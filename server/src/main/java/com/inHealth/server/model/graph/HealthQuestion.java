package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("HealthQuestion")
public class HealthQuestion implements Serializable {
    @Id
    private String id;
    private String text;
    private String options;
    private String type;

    @Relationship(type = "RELATED_TO", direction = Relationship.Direction.INCOMING)
    private HealthCondition healthCondition;


    @Relationship(type = "ANSWERED_HE", direction = Relationship.Direction.OUTGOING)
    private List<Answer> healthQuestions;



    // Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HealthCondition getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(HealthCondition healthCondition) {
        this.healthCondition = healthCondition;
    }

    public List<Answer> getHealthQuestions() {
        return healthQuestions;
    }

    public void setHealthQuestions(List<Answer> healthQuestions) {
        this.healthQuestions = healthQuestions;
    }

    public HealthQuestion(String id, String text, String options, String type) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.type = type;
    }

    public HealthQuestion(String id, String text, String options,  HealthCondition healthCondition) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.type = healthCondition.getCondition();
        this.healthCondition = healthCondition;
    }

    public HealthQuestion() {
    }
}
