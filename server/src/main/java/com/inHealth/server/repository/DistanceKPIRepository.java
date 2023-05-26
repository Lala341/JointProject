package com.inHealth.server.repository;

import com.inHealth.server.model.DistanceKPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DistanceKPIRepository extends MongoRepository<DistanceKPI, String> {
    List<DistanceKPI> findByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
            "{$match: { 'user': ?0, 'date': { $gte: ?1, $lte: ?2 } } }",
            "{$group: { '_id': null, 'totalDistance': { $sum: '$distance' } } }"
    })
    Double sumDistanceByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
            "{$match: { 'user': ?0, 'date': { $gte: ?1, $lte: ?2 } } }",
            "{$group: { '_id': null, 'averageDistance': { $avg: '$distance' } } }"
    })
    Double avgDistanceByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);

}
