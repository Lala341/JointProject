package com.inHealth.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("stepskpi")
public class StepsKPI {
    @Id
    private String id;
    private String user;
    private LocalDateTime date;
    private int steps;

    public StepsKPI() {
    }

    public StepsKPI(String id, String user, LocalDateTime date, int steps) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
