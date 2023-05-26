package com.inHealth.server.service;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.repository.DistanceKPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private DistanceKPIRepository distanceKPIRepository;

    public List<DistanceKPI> findWithinLastMonth(String user) {
        return distanceKPIRepository.findByUserAndDateBetween(user, LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1));
    }
}
