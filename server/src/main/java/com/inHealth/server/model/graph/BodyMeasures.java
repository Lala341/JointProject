package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;

@Node("BodyMeasures")
public class BodyMeasures implements Serializable {
    @Id
    private String id;
    private Double height;
    private Double weight;
    private String date;

    @Relationship(type = "HAS_BODY_MEASURES", direction = Relationship.Direction.OUTGOING)
    private Person person;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BodyMeasures(String id, Double height, Double weight, String date, Person person) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.date = date;
        this.person = person;
    }

    public BodyMeasures(String id, Double height, Double weight, String date) {
        this.height = height;
        this.weight = weight;
        this.date = date;
    }

    public BodyMeasures() {
    }
}
