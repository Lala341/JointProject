package com.inHealth.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document("users")
public class User {
    @Id
    private String id;
    private String gender;
    private int heightCm;
    private int weightKg;
    private String country;
    private LocalDate birthday;
    private String diseases;

    public User() {}

    public User(String id, String name, String gender, int heightCm, int weightKg, String country, LocalDate birthday, String diseases) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.country = country;
        this.birthday = birthday;
        this.diseases = diseases;
    }

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }



}