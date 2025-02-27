package com.example.ChatMicroservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDetailsDto {

    private Long senderId;
    private Long recipientId;
    private String content;
    private String timestamp;
    private Boolean read;
}
