package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;

@Node("PhysicalActivity")
public class PhysicalActivity implements Serializable {
    @Id
    private String id;
    private String date;
    private Integer sedentaryMinutes;
    private Integer moderateMinutes;
    private Integer vigorousMinutes;

    @Relationship(type = "HAS_PHYSICAL_ACTIVITY", direction = Relationship.Direction.INCOMING)
    private Person person;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSedentaryMinutes() {
        return sedentaryMinutes;
    }

    public void setSedentaryMinutes(Integer sedentaryMinutes) {
        this.sedentaryMinutes = sedentaryMinutes;
    }

    public int getModerateMinutes() {
        return moderateMinutes;
    }

    public void setModerateMinutes(Integer moderateMinutes) {
        this.moderateMinutes = moderateMinutes;
    }

    public int getVigorousMinutes() {
        return vigorousMinutes;
    }

    public void setVigorousMinutes(Integer vigorousMinutes) {
        this.vigorousMinutes = vigorousMinutes;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PhysicalActivity(String id, String date, Integer sedentaryMinutes, Integer moderateMinutes, Integer vigorousMinutes, Person person) {
        this.id = id;
        this.date = date;
        this.sedentaryMinutes = sedentaryMinutes;
        this.moderateMinutes = moderateMinutes;
        this.vigorousMinutes = vigorousMinutes;
        this.person = person;
    }

    public PhysicalActivity() {
    }
}

