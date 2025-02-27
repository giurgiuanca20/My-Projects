package com.example.DeviceManagement.kafka;

import com.example.DeviceManagement.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaProducer {


//    private final DeviceRepository deviceRepository;
//
//    @Autowired
//    public KafkaProducer(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
//
//    @Transactional
//    @KafkaListener(topics = "user-delete-topic", groupId = "device-group")
//    public void handleUserDeletion(String userId) {
//        Long id = Long.parseLong(userId);
//        deviceRepository.deleteUserIdByUserId(id);
//    }

}

