package com.inHealth.server.model.graph;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.util.List;

@Node("HabitQuestion")
public class HabitQuestion implements Serializable {
    @Id

    private String id;
    private String text;
    private String options;
    // Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public HabitQuestion() {
    }

    public HabitQuestion(String id, String text, String options) {
        this.id = id;
        this.text = text;
        this.options = options;
    }

    public HabitQuestion(String id) {
        this.id = id;
    }
}
