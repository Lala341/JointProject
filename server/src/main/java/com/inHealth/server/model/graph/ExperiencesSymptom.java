package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;

@RelationshipProperties
public class ExperiencesSymptom implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Symptom symptom;

    private String date;


    public ExperiencesSymptom() {
    }

    public ExperiencesSymptom(Symptom symptom, String date) {
        this.symptom = symptom;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
// Getters and setters
}
