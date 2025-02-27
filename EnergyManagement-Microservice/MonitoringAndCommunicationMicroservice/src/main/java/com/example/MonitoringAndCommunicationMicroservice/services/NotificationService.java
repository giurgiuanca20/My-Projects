package com.example.MonitoringAndCommunicationMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendNotification(String message,String deviceId) {
        System.out.println("Sending notification: " + message);
        template.convertAndSend("/topic/notifications/"+deviceId, message);
    }


}