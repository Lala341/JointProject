package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("HealthCondition")
public class HealthCondition implements Serializable {
    @Id
    private String id;
    private String condition;

    @Relationship(type = "RELATED_TO_SYMPTOM")
    private Symptom symptom;

    @Relationship(type = "RELATED_TO")
    private HealthQuestion healthQuestion;

    @Relationship(type = "HAS_PREEXISTING_CONDITION", direction = Relationship.Direction.INCOMING)
    private List<Person> persons;

    // Getters and setters
}
