package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("HealthCondition")
public class HealthCondition implements Serializable {
    @Id
    private String id;
    private String condition;

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

}

