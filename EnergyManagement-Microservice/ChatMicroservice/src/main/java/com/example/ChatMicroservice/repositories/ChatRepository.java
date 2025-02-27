package com.example.ChatMicroservice.repositories;

import com.example.ChatMicroservice.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
    @Query("SELECT c FROM ChatMessage c " +
            "WHERE (c.senderId = :userId1 AND c.recipientId = :userId2) " +
            "   OR (c.senderId = :userId2 AND c.recipientId = :userId1) " )
    List<ChatMessage> findMessagesBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);



    @Query("SELECT c FROM ChatMessage c " +
            "WHERE (c.senderId = :senderId AND c.recipientId = :recipientId) ")
    List<ChatMessage> findByUsers(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

    @Query("SELECT c FROM ChatMessage c " +
            "WHERE (c.senderId = :adminId) " +
            "   OR (c.recipientId = :adminId) " )
    List<ChatMessage> findMessagesForAUser(@Param("adminId") Long adminId);

}
