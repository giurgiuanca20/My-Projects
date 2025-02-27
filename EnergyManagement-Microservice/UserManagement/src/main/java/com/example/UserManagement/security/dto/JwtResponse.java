package com.example.UserManagement.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JwtResponse {

    private String token;
    private Long id;
    private String username;
    private List<String> roles;

}
