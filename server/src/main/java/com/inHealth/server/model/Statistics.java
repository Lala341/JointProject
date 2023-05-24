package com.inHealth.server.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("statistics")
public class Statistics {

    private String id;
    private String userId;
    private String date;
    private double avgAccX;
    private double avgAccY;
    private double avgAccZ;

    public Statistics(){}

    public Statistics(String id, String userId, String date, double avgAccX, double avgAccY, double avgAccZ) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.avgAccX = avgAccX;
        this.avgAccY = avgAccY;
        this.avgAccZ = avgAccZ;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAvgAccX() {
        return avgAccX;
    }

    public void setAvgAccX(double avgAccX) {
        this.avgAccX = avgAccX;
    }

    public double getAvgAccY() {
        return avgAccY;
    }

    public void setAvgAccY(double avgAccY) {
        this.avgAccY = avgAccY;
    }

    public double getAvgAccZ() {
        return avgAccZ;
    }

    public void setAvgAccZ(double avgAccZ) {
        this.avgAccZ = avgAccZ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
