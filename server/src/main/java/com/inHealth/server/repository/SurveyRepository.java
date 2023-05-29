package com.inHealth.server.repository;

import com.inHealth.server.model.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SurveyRepository extends MongoRepository<Survey, String> {
    Survey findFirstByOrderByDateDesc();
}
