package com.inHealth.server.repository.graph;
import com.inHealth.server.model.graph.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;



public interface PersonRepository extends Neo4jRepository<Person, String> {
}

