package com.inHealth.server.service;

import com.inHealth.server.model.ModelMetrics;
import com.inHealth.server.model.User;
import com.inHealth.server.repository.ModelMetricsRepository;
import com.inHealth.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMetricsService {

    @Autowired
    private ModelMetricsRepository modelRepository;

    public ModelMetrics save(ModelMetrics model) {

        return modelRepository.save(model);
    }
}
