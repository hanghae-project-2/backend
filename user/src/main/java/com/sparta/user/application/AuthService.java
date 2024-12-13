package com.sparta.user.application;

import com.sparta.user.application.dtos.AuthResponse;
import com.sparta.user.application.dtos.SignInRequest;
import com.sparta.user.application.dtos.SignUpRequest;
import com.sparta.user.config.CustomException;
import com.sparta.user.domain.User;
import com.sparta.user.domain.UserRepository;
import com.sparta.user.security.JwtUtil;
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
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));

        if (!user.getIsApproved()) {
            throw new CustomException("승인되지 않은 계정입니다.", HttpStatus.FORBIDDEN);
        }

        return jwtUtil.createAccessToken(user);
    }

    public void logout(HttpServletRequest request) {
        String token = jwtUtil.getTokenFromHeader("Authorization", request);

        if (token == null || !jwtUtil.validateToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }

        long expiration = jwtUtil.getRemainingExpiration(token);

        jwtBlacklistService.addToBlacklist(token, expiration);
    }

    public Boolean verifyUser(final String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional
    public void createUser(final SignUpRequest request) {
        userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .slackId(request.getSlackId())
                .isApproved(false)
                .isDelete(false)
                .createdBy(request.getUsername())
                .build());
    }
}
