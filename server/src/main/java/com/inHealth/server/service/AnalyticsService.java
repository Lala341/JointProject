package com.inHealth.server.service;

import com.inHealth.server.dto.ActivityCount;
import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.model.Statistics;
import com.inHealth.server.model.StepsKPI;
import com.inHealth.server.repository.DistanceKPIRepository;
import com.inHealth.server.repository.StatisticsRepository;
import com.inHealth.server.repository.StepsKPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private DistanceKPIRepository distanceKPIRepository;

    @Autowired
    private StepsKPIRepository stepsKPIRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

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

    public List<ActivityCount> activitiesByUserAndDateBetween(String user, LocalDate startDate, LocalDate endDate) {
        return statisticsRepository.activitiesByUserAndDateBetween(user, startDate, endDate);
    }
}
