package com.example.UserManagement.security;

import com.example.UserManagement.entities.Role;
import com.example.UserManagement.entities.User;
import com.example.UserManagement.repositories.UserRepository;
import com.example.UserManagement.security.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;
  private final AuthenticationManager authenticationManager;

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public void register(SignupRequest signUpRequest) {
    User user = new User(signUpRequest.getUsername(), signUpRequest.getName(),signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),signUpRequest.getRole());
    userRepository.save(user);
  }

  public Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }

}
