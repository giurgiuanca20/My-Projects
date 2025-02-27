package com.example.ChatMicroservice;

import com.example.ChatMicroservice.services.ChatService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatMicroserviceApplication.class, args);

	}

}
