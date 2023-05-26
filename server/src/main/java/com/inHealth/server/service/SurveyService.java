package com.inHealth.server.service;

import com.inHealth.server.model.Response;
import com.inHealth.server.model.Survey;
import com.inHealth.server.repository.ResponseRepository;
import com.inHealth.server.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SurveyService {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Survey getDailySurvey() {
        Survey survey = surveyRepository.findByDate(LocalDate.now());
        return survey;
    }
}
