package com.inHealth.server.model.graph;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Node("Person")
public class Person   implements Serializable {
    @Id
    private String id;
    private String name;

    @Relationship(type = "HAS_DEMOGRAPHICS", direction = Relationship.Direction.INCOMING)
    private Demographics demographics;

    @Relationship(type = "HAS_CONDITION")
    private  List<HealthCondition> healthCondition;

    @Relationship(type = "EXPERIENCES_SYMPTOM")
    private List<ExperiencesSymptom> experiencesSymptoms;

    @Relationship(type = "HAS_BODY_MEASURES")
    private List<BodyMeasures> bodyMeasures;

    @Relationship(type = "HAS_PHYSICAL_ACTIVITY")
    private  List<PhysicalActivity> physicalActivity;

    @Relationship(type = "HAS_PREEXISTING_CONDITION")
    private  List<HealthCondition> preexistingCondition;

    @Relationship(type = "HAS_RECOMENDATION")
    private  List<Recomendation> recomendation;

    @Relationship(type = "HAS_ANSWER", direction = Relationship.Direction.OUTGOING)
    private List<Answer> answers;


    // Getters and setters
    public void addAnswer(Answer answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answers.add(answer);
        answer.setPerson(this); // Set the reference to the parent entity
    }

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

    public Demographics getDemographics() {
        return demographics;
    }

    public void setDemographics(Demographics demographics) {
        this.demographics = demographics;
    }

    public List<HealthCondition> getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(List<HealthCondition> healthCondition) {
        this.healthCondition = healthCondition;
    }

    public List<ExperiencesSymptom> getExperiencesSymptoms() {
        return experiencesSymptoms;
    }

    public void setExperiencesSymptoms(List<ExperiencesSymptom> experiencesSymptoms) {
        this.experiencesSymptoms = experiencesSymptoms;
    }

    public List<BodyMeasures> getBodyMeasures() {
        return bodyMeasures;
    }

    public void setBodyMeasures(List<BodyMeasures> bodyMeasures) {
        this.bodyMeasures = bodyMeasures;
    }

    public List<PhysicalActivity> getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(List<PhysicalActivity> physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public List<HealthCondition> getPreexistingCondition() {
        return preexistingCondition;
    }

    public void setPreexistingCondition(List<HealthCondition> preexistingCondition) {
        this.preexistingCondition = preexistingCondition;
    }

    public List<Recomendation> getRecomendation() {
        return recomendation;
    }

    public void setRecomendation(List<Recomendation> recomendation) {
        this.recomendation = recomendation;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Person() {
    }
}
