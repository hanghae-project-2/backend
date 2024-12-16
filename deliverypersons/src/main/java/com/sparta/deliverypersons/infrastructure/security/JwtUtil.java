package com.sparta.deliverypersons.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Component
public class JwtUtil {

    private final Key key;

    // TODO ${jwt.secret} <- 이 부분 jwt key 추가하고 수정해야함.
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("유효하지 않은 JWT 토큰입니다.");
        }
    }

    public boolean hasRequiredRole(Claims claims, List<String> requiredRoles) {
        List<String> roles = claims.get("role", List.class);
        if (roles == null) {
            return false;
        }
        for (String role : requiredRoles) {
            if (roles.contains(role)) {
                return true;
            }
        }
        return false;
    }
}
