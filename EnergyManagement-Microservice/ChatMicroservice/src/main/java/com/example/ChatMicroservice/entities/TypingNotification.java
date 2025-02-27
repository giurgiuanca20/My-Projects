package com.example.ChatMicroservice.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypingNotification {
    private Long senderId;
    private Long recipientId;
}
