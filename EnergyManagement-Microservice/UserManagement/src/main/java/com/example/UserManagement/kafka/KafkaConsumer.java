package com.example.UserManagement.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate=kafkaTemplate;
    }

    public void deleteUser(Long userId) {
        kafkaTemplate.send("user-delete-topic", userId.toString());
    }
}
