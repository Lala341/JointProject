package com.inHealth.server.model.graph;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("Symptom")
public class Symptom implements Serializable {
    @Id
    private String id;
    private String symptom;

    @Relationship(type = "RELATED_TO_SYMPTOM", direction = Relationship.Direction.INCOMING)
    private HealthCondition condition;


    // Getters and setters


    public Symptom() {
    }

    public Symptom(String id, String symptom, HealthCondition condition) {
        this.id = id;
        this.symptom = symptom;
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public HealthCondition getCondition() {
        return condition;
    }

    public void setCondition(HealthCondition condition) {
        this.condition = condition;
    }


}
