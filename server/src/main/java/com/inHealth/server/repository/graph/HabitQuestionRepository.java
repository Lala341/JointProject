package com.inHealth.server.repository.graph;


import com.inHealth.server.model.graph.HabitQuestion;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface HabitQuestionRepository extends Neo4jRepository<HabitQuestion, String>{
}

