package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.DietBehaviorQuestion;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DietBehaviourQuestionRepository extends Neo4jRepository<DietBehaviorQuestion, String> {
}
