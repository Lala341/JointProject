package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("HealthCondition")
public class HealthCondition implements Serializable {
    @Id
    private String id;
    private String condition;

    @Relationship(type = "RELATED_TO_SYMPTOM", direction = Relationship.Direction.OUTGOING)
    private Symptom symptom;

    @Relationship(type = "RELATED_TO", direction = Relationship.Direction.OUTGOING)
    private List<HealthQuestion> healthQuestions;

    @Relationship(type = "HAS_PREEXISTING_CONDITION", direction = Relationship.Direction.INCOMING)
    private List<Person> persons;

    // Getters and setters


    public HealthCondition() {
    }

    public HealthCondition(String id, String condition) {
        this.id = id;
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }



    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<HealthQuestion> getHealthQuestions() {
        return healthQuestions;
    }

    public void setHealthQuestions(List<HealthQuestion> healthQuestions) {
        this.healthQuestions = healthQuestions;
    }
}

