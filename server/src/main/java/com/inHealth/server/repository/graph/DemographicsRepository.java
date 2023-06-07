package com.inHealth.server.repository.graph;
import com.inHealth.server.model.graph.Demographics;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DemographicsRepository extends Neo4jRepository<Demographics, String> {
}
