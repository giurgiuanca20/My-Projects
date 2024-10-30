package com.example.UserManagement.security.dto;

import com.example.UserManagement.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Builder
public class UserDetailsImpl implements UserDetails {

  private final Long id;
  private final String username;
  private final String email;
  @JsonIgnore
  private final String password;
  private final GrantedAuthority authority;

  public UserDetailsImpl(Long id, String username, String email, String password, GrantedAuthority authority) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authority = authority;
  }

  public static UserDetails build(User user) {
    GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().name());


    return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(authority);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
