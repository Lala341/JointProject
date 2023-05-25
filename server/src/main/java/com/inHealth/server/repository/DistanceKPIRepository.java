package com.inHealth.server.repository;

import com.inHealth.server.model.DistanceKPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DistanceKPIRepository extends MongoRepository<DistanceKPI, String> {
    List<DistanceKPI> findByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate);
}
