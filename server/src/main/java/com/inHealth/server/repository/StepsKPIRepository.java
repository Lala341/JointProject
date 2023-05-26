package com.inHealth.server.repository;

import com.inHealth.server.model.StepsKPI;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StepsKPIRepository extends MongoRepository<StepsKPI, String> {
    List<StepsKPI> findByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
            "{$match: { 'user': ?0, 'date': { $gte: ?1, $lte: ?2 } } }",
            "{$group: { '_id': null, 'totalSteps': { $sum: 'steps' } } }"
    })
    Integer sumStepsByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
            "{$match: { 'user': ?0, 'date': { $gte: ?1, $lte: ?2 } } }",
            "{$group: { '_id': null, 'averageSteps': { $avg: 'steps' } } }"
    })
    Integer avgStepsByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);


}
