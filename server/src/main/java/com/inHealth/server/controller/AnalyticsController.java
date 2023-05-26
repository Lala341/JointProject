package com.inHealth.server.controller;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.model.StepsKPI;
import com.inHealth.server.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;
    @GetMapping("/distance")
    public ResponseEntity<List<DistanceKPI>>distanceByUserAndDateBetween(@RequestParam("user") String user, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(analyticsService.distanceByUserAndDateBetween(user, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/distance/sum")
    public ResponseEntity<Double>sumDistanceByUserAndDateBetween(@RequestParam("user") String user, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(analyticsService.sumDistanceByUserAndDateBetween(user, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/distance/avg")
    public ResponseEntity<Double>avgDistanceByUserAndDateBetween(@RequestParam("user") String user, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(analyticsService.avgDistanceByUserAndDateBetween(user, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/steps")
    public ResponseEntity<List<StepsKPI>>stepsByUserAndDateBetween(@RequestParam("user") String user, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(analyticsService.stepsByUserAndDateBetween(user, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/steps/sum")
    public ResponseEntity<Integer>sumStepsByUserAndDateBetween(@RequestParam("user") String user, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(analyticsService.sumStepsByUserAndDateBetween(user, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/steps/avg")
    public ResponseEntity<Double>avgStepsByUserAndDateBetween(@RequestParam("user") String user, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(analyticsService.avgStepsByUserAndDateBetween(user, startDate, endDate), HttpStatus.OK);
    }
}
