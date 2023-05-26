package com.inHealth.server.controller;

import com.inHealth.server.model.Response;
import com.inHealth.server.model.Survey;
import com.inHealth.server.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;
    /*
    {
      "surveyId": "surveyId",
      "userId": "userId",
      "answers": [
        {
          "questionId": "questionId",
          "optionId": "optionId"
        }
      ]
    }
    */
    @PostMapping("/response")
    public ResponseEntity<Response> saveResponse(@RequestBody Response response) {
        return new ResponseEntity<>(surveyService.saveResponse(response), HttpStatus.OK);
    }
    /*
    {
      "title": "Customer Satisfaction Survey",
      "description": "Please provide your feedback on our products and services.",
      "date": "2023-05-23",
      "questions": [
        {
          "text": "How would you rate our product quality?",
          "options": [
            {
              "text": "Excellent"
            },
            {
              "text": "Good"
            },
            {
              "text": "Average"
            },
            {
              "text": "Poor"
            }
           ]
         }
       ]
    }
    */
    @PostMapping()
    public ResponseEntity<Survey> saveSurvey(@RequestBody Survey survey) {
        return new ResponseEntity<>(surveyService.saveSurvey(survey), HttpStatus.OK);
    }

    @GetMapping("/daily")
    public ResponseEntity<Survey> getDailySurvey() {
        return new ResponseEntity<>(surveyService.getDailySurvey(), HttpStatus.OK);
    }
}
