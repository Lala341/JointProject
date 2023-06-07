package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.Symptom;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SymptomRepository extends Neo4jRepository<Symptom, String> {
}

