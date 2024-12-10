package com.sparta.user.security;

import com.sparta.user.application.JwtBlacklistService;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "JWT 인증 필터")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtBlacklistService jwtBlacklistService;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromHeader(request);

        if (StringUtils.hasText(token)) {
            try {
                // 블랙리스트 확인
                if (jwtBlacklistService.isBlacklisted(token)) {
                    log.error("JWT token is blacklisted");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("{\"message\":\"Token is blacklisted\"}");
                    return;
                }

                Claims claims = jwtUtil.validateAndGetClaims(token);
                setAuthentication(claims.getSubject());
            } catch (ExpiredJwtException e) {
                log.error("Expired JWT token");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("{\"message\":\"Token expired\"}");
                return;
            } catch (JwtException e) {
                log.error("Invalid JWT token: {}", e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (StringUtils.hasText(header) && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }

    private void setAuthentication(String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Authentication set for user: {}", username);
    }
}
