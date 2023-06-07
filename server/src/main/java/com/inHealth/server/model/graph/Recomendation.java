package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;

@Node("Recomendation")
public class Recomendation implements Serializable {
    @Id
    private String id;
    private String type;
    private String description;
    private String date;

    @Relationship(type = "HAS_RECOMENDATION", direction = Relationship.Direction.INCOMING)
    private Person person;

    // Getters and setters
}

