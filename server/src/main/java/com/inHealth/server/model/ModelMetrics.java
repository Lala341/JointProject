package com.inHealth.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("models")
public class ModelMetrics {


    @Id
    private String id;
    private String type;
    private String version;
    private double trainerror;
    private double acurracy;
    private double precision;
    private double recall;
    private double f1score;
    private double classn;
    private String labelclass;
    private LocalDate date;

    public ModelMetrics() {
    }

    public ModelMetrics(String id, String type, String version, double trainerror, double acurracy, double precision, double recall, double f1score, LocalDate date, String labelclass,  double classn) {
        this.id = id;
        this.type = type;
        this.version = version;
        this.acurracy = acurracy;
        this.trainerror= trainerror;
        this.precision = precision;
        this.recall = recall;
        this.f1score = f1score;
        this.date = date;
        this.classn = classn;
        this.labelclass=labelclass;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getAcurracy() {
        return acurracy;
    }

    public void setAcurracy(double acurracy) {
        this.acurracy = acurracy;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getF1score() {
        return f1score;
    }

    public void setF1score(double f1score) {
        this.f1score = f1score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getTrainerror() {
        return trainerror;
    }

    public void setTrainerror(double trainerror) {
        this.trainerror = trainerror;
    }
    @Override
    public String toString() {
        return "ModelMetrics{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ", trainerror=" + trainerror +
                ", acurracy=" + acurracy +
                ", precision=" + precision +
                ", recall=" + recall +
                ", f1score=" + f1score +
                ", classn=" + classn +
                ", labelclass='" + labelclass + '\'' +
                ", date=" + date +
                '}';
    }


}
