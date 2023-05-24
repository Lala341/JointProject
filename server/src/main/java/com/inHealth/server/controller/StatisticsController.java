package com.inHealth.server.controller;

import com.inHealth.server.model.User;
import com.inHealth.server.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @GetMapping()
    public ResponseEntity<User> getStatistics() {

        statisticsService.calculateStatistics();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
