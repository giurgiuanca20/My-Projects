package com.example.ChatMicroservice.dtos.builders;

import com.example.ChatMicroservice.dtos.ChatDetailsDto;
import com.example.ChatMicroservice.entities.ChatMessage;


public class ChatBuilder {
    public static ChatMessage toChat(ChatDetailsDto chatDetailsDto){
        return new ChatMessage(chatDetailsDto.getSenderId(),chatDetailsDto.getRecipientId(),chatDetailsDto.getContent(),chatDetailsDto.getTimestamp());
    }
}
