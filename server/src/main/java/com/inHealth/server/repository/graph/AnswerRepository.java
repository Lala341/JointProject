package com.inHealth.server.repository.graph;

import com.inHealth.server.model.graph.Answer;
import com.inHealth.server.model.graph.HealthQuestion;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends Neo4jRepository<Answer, String> {
    @Query("MATCH (a:Answer)<-[:ANSWERED_HE]-(h:HealthQuestion{id:$questionId}) RETURN a")
    List<Answer> findByHealthQuestion(@Param("questionId") String questionId);

}
