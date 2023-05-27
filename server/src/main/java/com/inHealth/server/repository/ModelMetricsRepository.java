package com.inHealth.server.repository;

import com.inHealth.server.model.ModelMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelMetricsRepository extends MongoRepository<ModelMetrics, String> {

}
