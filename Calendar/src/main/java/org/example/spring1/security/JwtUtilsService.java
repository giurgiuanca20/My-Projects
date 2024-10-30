package org.example.spring1.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.example.spring1.user.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@Component
public class JwtUtilsService {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtilsService.class);

  private final String jwtSecret;
  private final int jwtExpirationMs;

  @Autowired
  public JwtUtilsService(@Value("${app.jwtSecret}") String jwtSecret,
                         @Value("${app.jwtExpirationMs}") int jwtExpirationMs) {
    this.jwtSecret = jwtSecret;
    this.jwtExpirationMs = jwtExpirationMs;
  }

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
            .subject((userPrincipal.getUsername()))
            .issuedAt(new Date())
            .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(getSignKey(), Jwts.SIG.HS512)
            .compact();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      getUsernameFromJwtToken(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  public String getUsernameFromJwtToken(String jwt) {
    return Jwts
            .parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(jwt)
            .getPayload()
            .getSubject();
  }

  private SecretKey getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

    return Keys.hmacShaKeyFor(keyBytes);
  }

}