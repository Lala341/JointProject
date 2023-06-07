package com.inHealth.server.service;

import com.inHealth.server.service.BiometricService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaCon {

    private final String KAFKA_TOPIC = "file-requests";
    private final String KAFKA_TOPIC_UPDATE = "update-data-graph";

    @Autowired
    private BiometricService biometricService;

    @KafkaListener(topics = KAFKA_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeFileRequest(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String name = record.key();
        String content = record.value();

        System.out.println("executing consumer");
        try{
            // Process the file request asynchronously
            biometricService.uploadToHdfs(content, name);

        } catch (Exception e){
            System.out.println(e);
        }

        // Manually acknowledge the message to mark it as processed
        acknowledgment.acknowledge();
    }

}
