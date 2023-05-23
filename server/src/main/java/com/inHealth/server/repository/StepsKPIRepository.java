package com.inHealth.server.repository;

import com.inHealth.server.model.StepsKPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepsKPIRepository extends MongoRepository<StepsKPI, String> {
}
