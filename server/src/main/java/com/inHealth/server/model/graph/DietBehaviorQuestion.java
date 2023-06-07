package com.inHealth.server.model.graph;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.List;

@Node("DietBehaviorQuestion")
public class DietBehaviorQuestion implements Serializable {
    @Id
    private String id;
    private String text;
    private String options;


    @Relationship(type = "ANSWERED_D", direction = Relationship.Direction.OUTGOING)
    private List<Answer> dietanswers;
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

    

    public DietBehaviorQuestion() {
    }

    public DietBehaviorQuestion(String id, String text, String options) {
        this.id = id;
        this.text = text;
        this.options = options;
    }
}
