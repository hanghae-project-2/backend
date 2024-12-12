package com.sparta.user.infrastructure.security;

import com.sparta.user.presentation.dto.response.AuthResponse;
import com.sparta.user.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JWT 관련 로그")
@Component
public class JwtUtil {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final long REFRESH_TOKEN_TIME = 14 * 24 * 60 * 60 * 1000L; // 14 days

    @Value("${service.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(decodedKey);
    }

    public AuthResponse createAccessToken(final User user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("tokenType", "access")
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(key, signatureAlgorithm)
                .compact();

        return AuthResponse.of(BEARER_PREFIX + token);
    }

    public String createRefreshToken(String username) {
        return BEARER_PREFIX + Jwts.builder()
                .setSubject(username)
                .claim("tokenType", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.error("유효하지 않은 JWT token: {}", e.getMessage());
        }
        return false;
    }

    public Claims validateAndGetClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            log.error("유효하지 않은 JWT token: {}", e.getMessage());
            throw e;
        }
    }

    public long getRemainingExpiration(String token) {
        Claims claims = validateAndGetClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.getTime() - System.currentTimeMillis();
    }

    public String getTokenFromHeader(String headerName, jakarta.servlet.http.HttpServletRequest request) {
        String header = request.getHeader(headerName);
        if (StringUtils.hasText(header) && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public String getTokenType(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("tokenType", String.class);
        } catch (Exception e) {
            return null;
        }
    }

}
