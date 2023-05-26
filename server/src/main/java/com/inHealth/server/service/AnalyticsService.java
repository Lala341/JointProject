package com.inHealth.server.service;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.model.StepsKPI;
import com.inHealth.server.repository.DistanceKPIRepository;
import com.inHealth.server.repository.StepsKPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private DistanceKPIRepository distanceKPIRepository;

    @Autowired
    private StepsKPIRepository stepsKPIRepository;

    public List<DistanceKPI> distanceByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return distanceKPIRepository.findByUserAndDateBetween(user, startDate, endDate);
    }

    public Double sumDistanceByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return distanceKPIRepository.sumDistanceByUserAndDateBetween(user, startDate, endDate);
    }

    public Double avgDistanceByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return distanceKPIRepository.avgDistanceByUserAndDateBetween(user, startDate, endDate);
    }

    public List<StepsKPI> stepsByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return stepsKPIRepository.findByUserAndDateBetween(user, startDate, endDate);
    }

    public Integer sumStepsByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return stepsKPIRepository.sumStepsByUserAndDateBetween(user, startDate, endDate);
    }

    public Integer avgStepsByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return stepsKPIRepository.avgStepsByUserAndDateBetween(user, startDate, endDate);
    }
}
