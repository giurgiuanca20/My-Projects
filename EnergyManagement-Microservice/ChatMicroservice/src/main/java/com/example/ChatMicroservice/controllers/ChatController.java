package com.example.ChatMicroservice.controllers;

import com.example.ChatMicroservice.dtos.ChatDetailsDto;
import com.example.ChatMicroservice.entities.ChatMessage;
import com.example.ChatMicroservice.entities.ReadingNotification;
import com.example.ChatMicroservice.entities.TypingNotification;
import com.example.ChatMicroservice.services.ChatService;
import com.example.ChatMicroservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/chat")
public class ChatController {

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/typing/{recipientId}")
    public void notifyTyping(@Payload Map<String, Object> payload, @DestinationVariable Long recipientId) {
        Long senderId = Long.valueOf(payload.get("senderId").toString());
        notificationService.sendTypingNotification(senderId, recipientId);
    }

    @PostMapping("/read")
    public void read(@RequestBody ReadingNotification readingNotification){
        notificationService.sendReading(readingNotification.getSenderId(), readingNotification.getRecipientId());
        chatService.markAllMessagesAsRead(readingNotification.getSenderId(),readingNotification.getRecipientId());
    }

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService=chatService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ChatDetailsDto chatDetailsDto){
        chatService.create(chatDetailsDto);
        return new ResponseEntity<>("Message sent!",HttpStatus.CREATED);
    }

    @GetMapping("/messagesBetweenUsers")
    public ResponseEntity<List<ChatMessage>> getChatMessagesBetweenUsers(@RequestParam Long userId1,@RequestParam Long userId2){
        List<ChatMessage> chatMessages=chatService.getChatMessagesBetweenUsers(userId1,userId2);

        if (chatMessages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println(chatMessages);
        return new ResponseEntity<>(chatMessages,HttpStatus.FOUND);
    }

    @GetMapping("/usersForAdmin")
    public ResponseEntity<List<Long>> getUsersForAdminChat(@RequestParam Long adminId){
        List<Long> users=chatService.getUsersForAdminChat(adminId);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.FOUND);
    }
}
