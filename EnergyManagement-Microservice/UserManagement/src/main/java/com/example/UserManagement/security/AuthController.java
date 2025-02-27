package com.example.UserManagement.security;

import com.example.UserManagement.security.dto.JwtResponse;
import com.example.UserManagement.security.dto.LoginRequest;
import com.example.UserManagement.security.dto.SignupRequest;
import com.example.UserManagement.security.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

  private final AuthService authService;
  private final JwtUtilsService jwtUtilsService;

  @PostMapping("/sign_in")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authService.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtilsService.generateJwtToken(authentication);


    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String role = userDetails.getAuthority().toString();

    return ResponseEntity.ok(
        JwtResponse.builder()
            .token(jwt)
            .id(userDetails.getId())
            .username(userDetails.getUsername())
            .roles(Collections.singletonList(role))
            .build());
  }


  @PostMapping("/sign_up")
  public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (authService.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>("Error: Username is already taken!",HttpStatus.BAD_REQUEST);
    }
    if (authService.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>("Error: Email is already in use!",HttpStatus.BAD_REQUEST);
    }
    authService.register(signUpRequest);
    return new ResponseEntity<>("User registered successfully!",HttpStatus.OK);
  }


}
