package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.Recomendation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RecomendationRepository extends Neo4jRepository<Recomendation, String> {
}
