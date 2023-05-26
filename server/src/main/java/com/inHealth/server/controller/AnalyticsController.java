package com.inHealth.server.controller;

import com.inHealth.server.model.DistanceKPI;
import com.inHealth.server.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/month")
    public ResponseEntity<List<DistanceKPI>>findWithinLastMonth(@RequestParam("user") String user) {
        return new ResponseEntity<>(analyticsService.findWithinLastMonth(user), HttpStatus.OK);
    }
}
