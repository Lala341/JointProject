package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.HealthQuestion;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HealthQuestionRepository extends Neo4jRepository<HealthQuestion, String> {
}
