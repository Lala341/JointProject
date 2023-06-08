package com.inHealth.server.service;

import com.inHealth.server.model.graph.Symptom;
import com.inHealth.server.repository.graph.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomService {
    @Autowired
    SymptomRepository symptomRepository;

    public List<Symptom> findAll() {
        return symptomRepository.findAll();
    }

}
