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

    // Getters and setters
}
