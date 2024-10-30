package com.example.UserManagement.dtos.builders;

import com.example.UserManagement.dtos.UserDetailsDto;
import com.example.UserManagement.entities.User;

public class UserBuilder {

    public static User toUser(UserDetailsDto userDetailsDto){
        return new User(userDetailsDto.getUsername(), userDetailsDto.getName(), userDetailsDto.getEmail(), userDetailsDto.getPassword(), userDetailsDto.getRole());
    }

}
