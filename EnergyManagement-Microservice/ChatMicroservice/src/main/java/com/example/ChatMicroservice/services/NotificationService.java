package com.example.ChatMicroservice.services;

import com.example.ChatMicroservice.entities.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;


    public void sendNotification(ChatMessage message, Long recipientId) {
        System.out.println("Sending notification: " + message);
        template.convertAndSend("/topic/notifications/"+recipientId, message);
    }



    public void sendTypingNotification(Long senderId, Long recipientId) {
        template.convertAndSend("/topic/notifications/" + recipientId,
                Map.of("type", "TYPING", "senderId", senderId));
    }

    public void sendReading(Long senderId, Long recipientId) {
        System.out.println("/topic/notifications/read/"+recipientId+"/"+senderId);
        String read="read";
        template.convertAndSend("/topic/notifications/read/"+recipientId+"/"+senderId,read);

    }


}