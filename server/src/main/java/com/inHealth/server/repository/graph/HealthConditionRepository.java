package com.inHealth.server.repository.graph;
import com.inHealth.server.model.graph.HealthCondition;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HealthConditionRepository extends Neo4jRepository<HealthCondition, String> {
}

