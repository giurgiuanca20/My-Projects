package com.example.ChatMicroservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chat_id")
    private Long id;
    @Column(nullable = false)
    private Long senderId;
    @Column(nullable = false)
    private Long recipientId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String timestamp;
    @Column(nullable = false)
    private boolean read;

    public ChatMessage(Long senderId, Long recipientId, String content, String timestamp) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = timestamp;
        this.read=false;
    }

    public ChatMessage() {
    }

}
