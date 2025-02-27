package com.example.ChatMicroservice.services;

import com.example.ChatMicroservice.dtos.ChatDetailsDto;
import com.example.ChatMicroservice.dtos.builders.ChatBuilder;
import com.example.ChatMicroservice.entities.ChatMessage;
import com.example.ChatMicroservice.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    public final ChatRepository chatRepository;
    private NotificationService notificationService;

    @Autowired
    public ChatService(ChatRepository chatRepository, NotificationService notificationService){
        this.chatRepository=chatRepository;
        this.notificationService=notificationService;
    }
    

    public void markAllMessagesAsRead(Long senderId, Long recipientId) {
        List<ChatMessage> messages = chatRepository.findByUsers(senderId, recipientId);
        if (messages != null && !messages.isEmpty()) {
            for (ChatMessage message : messages) {
                message.setRead(true);
            }
            chatRepository.saveAll(messages);
        }
    }




    public List<ChatMessage> getChatMessagesBetweenUsers(Long userId1, Long userId2) {
        return chatRepository.findMessagesBetweenUsers(userId1, userId2);
    }

    public List<Long> getUsersForAdminChat(Long adminId) {
         List<ChatMessage> messages=chatRepository.findMessagesForAUser(adminId);
        return messages.stream()
                .flatMap(message -> List.of(message.getSenderId(), message.getRecipientId()).stream()) // Adaugă sender și recipient
                .filter(userId -> !userId.equals(adminId)) // Exclude adminId
                .distinct() // Asigură unicitatea
                .collect(Collectors.toList());
    }

    public ChatMessage create(ChatDetailsDto chatDetailsDto) {
        ChatMessage chatMessage = ChatBuilder.toChat(chatDetailsDto);

        notificationService.sendNotification(chatMessage, chatDetailsDto.getRecipientId());

        return chatRepository.save(chatMessage);
    }

}
