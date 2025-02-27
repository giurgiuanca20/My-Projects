package com.example.ChatMicroservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

  private final JwtUtilsService jwtUtilsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

    String jwt = parseJwt(request);

    if (jwt != null && jwtUtilsService.validateJwtToken(jwt)) {
      String username = jwtUtilsService.getUsernameFromJwtToken(jwt);

      UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(username, null, null);  //Creează un obiect UsernamePasswordAuthenticationToken pentru utilizator și îl setează în SecurityContextHolder.
      SecurityContextHolder.getContext().setAuthentication(authentication);                 //Acest pas asigură că utilizatorul este considerat autentificat în contextul cererii curente.
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);     //extrage tokenul, ignora primele 7 caractere (Bearer)
    }

    return null;
  }
}
