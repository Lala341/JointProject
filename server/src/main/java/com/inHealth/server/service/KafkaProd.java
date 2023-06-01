package com.inHealth.server.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProd {

    private final String KAFKA_TOPIC = "file-requests";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceFileRequestToKafka(String content, String name) {
        // Produce the request message to Kafka topic
        kafkaTemplate.send(new ProducerRecord<>(KAFKA_TOPIC, name, content));
    }
}
