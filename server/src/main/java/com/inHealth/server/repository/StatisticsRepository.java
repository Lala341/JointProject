package com.inHealth.server.repository;

import com.inHealth.server.model.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<Statistics, String> {
}
