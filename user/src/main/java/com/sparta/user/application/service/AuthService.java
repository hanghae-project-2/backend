package com.sparta.user.application.service;

import com.sparta.user.presentation.dto.response.AuthResponse;
import com.sparta.user.presentation.dto.request.SignInRequest;
import com.sparta.user.presentation.dto.request.SignUpRequest;
import com.sparta.user.common.CustomException;
import com.sparta.user.domain.User;
import com.sparta.user.domain.UserRepository;
import com.sparta.user.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final JwtBlacklistService jwtBlacklistService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signIn(SignInRequest request) {
        String username = request.getUsername();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, request.getPassword())
        );

        User user = userRepository.findByUsername(username)
                .filter(userInfo -> passwordEncoder.matches(request.getPassword(), userInfo.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Username을 찾을 수 없습니다."));

        if (!user.getIsApproved()) {
            throw new CustomException("승인되지 않은 계정입니다.", HttpStatus.FORBIDDEN);
        }

        return jwtUtil.createAccessToken(user);
    }

    public void logout(HttpServletRequest request) {
        String token = jwtUtil.getTokenFromHeader("Authorization", request);

        if (token == null || !jwtUtil.validateToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않거나 만료된 토큰입니다.");
        }

        // 토큰 타입 확인 (Access Token 또는 Refresh Token)
        String tokenType = jwtUtil.getTokenType(token);
        if (tokenType == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 타입이 확인되지 않았습니다.");
        }

        long expiration = jwtUtil.getRemainingExpiration(token);

        boolean isAccessToken = "access".equals(tokenType);
        jwtBlacklistService.addToBlacklist(token, expiration, isAccessToken);
    }

    public Boolean verifyUser(final String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional
    public void createUser(final SignUpRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new CustomException("중복된 Username", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findBySlackId(request.getSlackId()).isPresent()) {
            throw new CustomException("중복된 SlackId", HttpStatus.BAD_REQUEST);
        }

        User user = User.create(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getSlackId(),
                null,
                request.getUsername()
        );

        userRepository.save(user);
    }
}
