package com.inHealth.server.controller;

import com.inHealth.server.service.DescriptiveAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController()
@RequestMapping("/descriptive")
public class DescriptiveAnalysisController {
    @Autowired
    private DescriptiveAnalysisService descriptiveAnalysisService;

    @GetMapping("/distance")
    public ResponseEntity<Double> getDailyDistance(@RequestParam("user") String user, @RequestParam("date") String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            double totalDistance = descriptiveAnalysisService.calculateDistance(user, date);
            return new ResponseEntity<>(totalDistance, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/steps")
    public ResponseEntity<Integer> getDailySteps(@RequestParam("user") String user, @RequestParam("date") String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            int totalSteps = descriptiveAnalysisService.calculateTotalSteps(user, date, 1.5, 50, 25);
            return new ResponseEntity<>(totalSteps, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}
