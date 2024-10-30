package com.example.UserManagement.security.dto;

import com.example.UserManagement.entities.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
  private String username;
  private String name;
  private String email;
  private String password;
  private Role role;
}
