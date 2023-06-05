package com.inHealth.server.model.graph;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("Symptom")
public class Symptom implements Serializable {
    @Id
    private String id;
    private String symptom;

    @Relationship(type = "EXPERIENCES_SYMPTOM", direction = Relationship.Direction.OUTGOING)
    private List<Person> persons;

    // Getters and setters
}
