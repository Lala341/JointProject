package com.inHealth.server.repository;

import com.inHealth.server.dto.ActivityCount;
import com.inHealth.server.model.Statistics;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import scala.Tuple2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface StatisticsRepository extends MongoRepository<Statistics, String> {

    @Aggregation(pipeline = {
            "{$match: { 'userId': ?0, 'date': { $gte: ?1, $lte: ?2 } } }",
            "{$group: { '_id': '$predictActivityM2', 'count': { $count: {} } } }",
            "{$project: { '_id': 0, 'activity': '$_id', 'count': 1 } }"
    })

    List<ActivityCount> activitiesByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);
}
