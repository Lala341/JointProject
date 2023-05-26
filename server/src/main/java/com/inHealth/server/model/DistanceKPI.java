package com.inHealth.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("distancekpi")
public class DistanceKPI {
    @Id
    private String id;
    private String user;
    private LocalDateTime date;
    private double distance;

    public DistanceKPI() {
    }

    public DistanceKPI(String id, String user, LocalDateTime date, double distance) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.distance = distance;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
