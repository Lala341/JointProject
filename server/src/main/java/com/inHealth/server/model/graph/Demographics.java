package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;

@Node("Demographics")
public class Demographics implements Serializable {
    @Id
    private String id;
    private String gender;
    private int age;
    private String race;
    private String country;

    @Relationship(type = "HAS_DEMOGRAPHICS", direction = Relationship.Direction.OUTGOING)
    private Person person;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Demographics(String id, String gender, int age, String race, String country, Person person) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.race = race;
        this.country = country;
        this.person = person;
    }

    public Demographics() {
    }
}

