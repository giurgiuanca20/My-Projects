package com.example.UserManagement.services;

import com.example.UserManagement.controllers.handlers.UserNotFoundException;
import com.example.UserManagement.dtos.UserDetailsDto;
import com.example.UserManagement.dtos.builders.UserBuilder;
import com.example.UserManagement.entities.User;
import com.example.UserManagement.kafka.KafkaConsumer;
import com.example.UserManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final KafkaConsumer kafkaConsumer;

    @Autowired
    public UserService(UserRepository userRepository,KafkaConsumer kafkaConsumer) {
        this.userRepository = userRepository;
        this.kafkaConsumer=kafkaConsumer;
    }

    @Transactional
    public void delete(String username) {

        userRepository.findByUsername(username).orElseThrow(() -> {
            return new UserNotFoundException("Can't delete. User not found!");
        });
        kafkaConsumer.deleteUser(userRepository.findByUsername(username).get().getId());
        userRepository.deleteByUsername(username);
    }

    public User findById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new UserNotFoundException("User not found!");
        });
        return user;

    }

    public List<User> findAll() {

        List<User> users = userRepository.findAll();
        return users;

    }

    public Long findIdByUsername(String username){
        return userRepository.findIdByUsername(username);
    }

    public User update(UserDetailsDto userDetailsDto) {
        User auxUser=userRepository.findByUsername(userDetailsDto.getUsername()).get();
        if(userRepository.findByUsername(userDetailsDto.getUsername()).isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        User user = UserBuilder.toUser(userDetailsDto);
        user.setId(auxUser.getId());
        return userRepository.save(user);
    }

}
