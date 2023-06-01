package com.inHealth.server.service;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.model.Statistics;
import com.inHealth.server.model.StepsKPI;
import com.inHealth.server.repository.BiometricRepository;
import com.inHealth.server.repository.DistanceKPIRepository;
import com.inHealth.server.repository.StatisticsRepository;
import com.inHealth.server.repository.StepsKPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static java.lang.Math.min;


@Service
public class BiometricService {

    @Autowired
    private BiometricRepository biometricRepository;

    @Autowired
    private DescriptiveAnalysisService descriptiveAnalysisService;

    @Autowired
    private DistanceKPIRepository distanceKPIRepository;

    @Autowired
    private StepsKPIRepository stepsKPIRepository;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsRepository statisticsRepository;

    public void uploadToHdfs(String content, String name) throws IOException {
        String user = name.split("_")[min(1,name.split("_").length)];
        String fileName = name+".txt";
        // Calculate total distance
        biometricRepository.uploadToHdfs(content, user, fileName);
        LocalDateTime date=LocalDateTime.now();
        // Calculate statistics
        List<Statistics> statisticsList = statisticsService.calculateStatistics(user, fileName);
        // Store statistics in MongoDB
        for(Statistics statistic: statisticsList ) {
            statisticsRepository.save(statistic);
            date=statistic.getDate();
        }

        double totalDistance = descriptiveAnalysisService.calculateDistance(user, fileName);
        // Store the calculated KPI in MongoDB
        DistanceKPI distanceKPI = new DistanceKPI(null, user, date, totalDistance);
        distanceKPIRepository.save(distanceKPI);
        // Calculate total steps
        int totalSteps = descriptiveAnalysisService.calculateTotalSteps(user, fileName, 1.5, 50, 25);
        // Store the calculated KPI in MongoDB
        StepsKPI stepsKPI = new StepsKPI(null, user, date, totalSteps);
        stepsKPIRepository.save(stepsKPI);
    }
}
