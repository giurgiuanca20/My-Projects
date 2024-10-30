package com.example.UserManagement.dtos;

import com.example.UserManagement.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetailsDto {
    private String username;
    private String name;
    private String email;
    private String password;
    private Role role;

    public UserDetailsDto(String username, String name, String email, String password, Role role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
