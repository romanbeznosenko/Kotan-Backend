package com.kotanapp.kotanappapi.utils.jwt;

import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class JwtService {

    private final SecretKey secretKey;
    private static final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 1000L; // 15 minutes
    private static final long REFRESH_TOKEN_VALIDITY = 7L * 24 * 60 * 60 * 1000L; // 7 days
    private static final int STAY_SIGNED_IN_MULTIPLIER = 5;

    public JwtService(@Value("${jwt.secret.key}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UUID userId, UUID authId, String email, UserTypeEnum userType, boolean staySignedIn) {
        long validity = staySignedIn ? ACCESS_TOKEN_VALIDITY * STAY_SIGNED_IN_MULTIPLIER : ACCESS_TOKEN_VALIDITY;

        return Jwts.builder()
                .claim("user_id", userId.toString())
                .claim("auth_id", authId.toString())
                .claim("email", email)
                .claim("role", userType.name())
                .claim("type", "access")
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UUID userId, UUID authId, String email, UserTypeEnum userType, boolean staySignedIn) {
        long validity = staySignedIn ? REFRESH_TOKEN_VALIDITY * STAY_SIGNED_IN_MULTIPLIER : REFRESH_TOKEN_VALIDITY;

        return Jwts.builder()
                .claim("user_id", userId.toString())
                .claim("auth_id", authId.toString())
                .claim("email", email)
                .claim("role", userType.name())
                .claim("type", "refresh")
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UUID extractUserId(String token) {
        Claims claims = extractClaims(token);
        return UUID.fromString(claims.get("user_id", String.class));
    }

    public UUID extractAuthId(String token) {
        Claims claims = extractClaims(token);
        return UUID.fromString(claims.get("auth_id", String.class));
    }

    public String extractEmail(String token) {
        Claims claims = extractClaims(token);
        return claims.get("email", String.class);
    }

    public UserTypeEnum extractUserType(String token) {
        Claims claims = extractClaims(token);
        String role = claims.get("role", String.class);
        return UserTypeEnum.valueOf(role);
    }

    public boolean isRefreshToken(String token) {
        Claims claims = extractClaims(token);
        String type = claims.get("type", String.class);
        return "refresh".equals(type);
    }

    public boolean isAccessToken(String token) {
        Claims claims = extractClaims(token);
        String type = claims.get("type", String.class);
        return "access".equals(type);
    }
}
