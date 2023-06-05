package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.BodyMeasures;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BodyMeasuresRepository extends Neo4jRepository<BodyMeasures, String> {
}

