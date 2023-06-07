package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.PhysicalActivity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PhysicalActivityRepository extends Neo4jRepository<PhysicalActivity, String> {
}

