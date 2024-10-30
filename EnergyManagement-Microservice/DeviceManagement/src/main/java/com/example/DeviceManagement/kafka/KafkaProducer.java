package com.example.DeviceManagement.kafka;

import com.example.DeviceManagement.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final DeviceRepository deviceRepository;

    @Autowired
    public KafkaProducer(DeviceRepository deviceRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.deviceRepository = deviceRepository;
        this.kafkaTemplate=kafkaTemplate;
    }

    //sterge mappingul daca userul e sters
    @Transactional
    @KafkaListener(topics = "user-delete-topic", groupId = "device-group")
    public void handleUserDeletion(String userId) {
        Long id = Long.parseLong(userId);
        deviceRepository.deleteUserIdByUserId(id);
    }

}

