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
    private double avgGyroX;
    private double avgGyroY;
    private double avgGyroZ;
    private double stdDevAccX;
    private double stdDevAccY;
    private double stdDevAccZ;
    private double stdDevGyroX;
    private double stdDevGyroY;
    private double stdDevGyroZ;

    private String predictActivityM1;
    private String predictActivityM2;

    public Statistics(){}

    public Statistics(String id, String userId, String date, double avgAccX, double avgAccY, double avgAccZ, double avgGyroX, double avgGyroY, double avgGyroZ, double stdDevAccX, double stdDevAccY, double stdDevAccZ, double stdDevGyroX, double stdDevGyroY, double stdDevGyroZ, String predictActivityM1, String predictActivityM2) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.avgAccX = avgAccX;
        this.avgAccY = avgAccY;
        this.avgAccZ = avgAccZ;
        this.avgGyroX = avgGyroX;
        this.avgGyroY = avgGyroY;
        this.avgGyroZ = avgGyroZ;
        this.stdDevAccX = stdDevAccX;
        this.stdDevAccY = stdDevAccY;
        this.stdDevAccZ = stdDevAccZ;
        this.stdDevGyroX = stdDevGyroX;
        this.stdDevGyroY = stdDevGyroY;
        this.stdDevGyroZ = stdDevGyroZ;
        this.predictActivityM1=predictActivityM1;
        this.predictActivityM2=predictActivityM2;
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

    public double getAvgGyroX() {
        return avgGyroX;
    }

    public void setAvgGyroX(double avgGyroX) {
        this.avgGyroX = avgGyroX;
    }

    public double getAvgGyroY() {
        return avgGyroY;
    }

    public void setAvgGyroY(double avgGyroY) {
        this.avgGyroY = avgGyroY;
    }

    public double getAvgGyroZ() {
        return avgGyroZ;
    }

    public void setAvgGyroZ(double avgGyroZ) {
        this.avgGyroZ = avgGyroZ;
    }

    public double getStdDevAccX() {
        return stdDevAccX;
    }

    public void setStdDevAccX(double stdDevAccX) {
        this.stdDevAccX = stdDevAccX;
    }

    public double getStdDevAccY() {
        return stdDevAccY;
    }

    public void setStdDevAccY(double stdDevAccY) {
        this.stdDevAccY = stdDevAccY;
    }

    public double getStdDevAccZ() {
        return stdDevAccZ;
    }

    public void setStdDevAccZ(double stdDevAccZ) {
        this.stdDevAccZ = stdDevAccZ;
    }

    public double getStdDevGyroX() {
        return stdDevGyroX;
    }

    public void setStdDevGyroX(double stdDevGyroX) {
        this.stdDevGyroX = stdDevGyroX;
    }

    public double getStdDevGyroY() {
        return stdDevGyroY;
    }

    public void setStdDevGyroY(double stdDevGyroY) {
        this.stdDevGyroY = stdDevGyroY;
    }

    public double getStdDevGyroZ() {
        return stdDevGyroZ;
    }

    public void setStdDevGyroZ(double stdDevGyroZ) {
        this.stdDevGyroZ = stdDevGyroZ;
    }

    public String getPredictActivityM1() {
        return predictActivityM1;
    }

    public void setPredictActivityM1(String predictActivityM1) {
        this.predictActivityM1 = predictActivityM1;
    }


    public String getPredictActivityM2() {
        return predictActivityM2;
    }

    public void setPredictActivityM2(String predictActivityM2) {
        this.predictActivityM2 = predictActivityM2;
    }

}
