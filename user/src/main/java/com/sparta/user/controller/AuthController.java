package com.sparta.user.controller;

import com.sparta.user.application.AuthService;
import com.sparta.user.application.dtos.AuthResponse;
import com.sparta.user.application.dtos.SignInRequest;
import com.sparta.user.application.dtos.SignUpRequest;
import com.sparta.user.domain.UserRole;
import com.sparta.user.security.CustomUserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(final @RequestBody SignInRequest request) {
        final AuthResponse response = authService.signIn(request);
        return createResponse(ResponseEntity.ok(response));
    }

    @PostMapping("/signOut")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Successfully logged out");
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        authService.createUser(request);
        return createResponse(ResponseEntity.ok("회원가입 성공"));
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyUser(final @RequestParam(value = "username") String username) {
        Boolean response = authService.verifyUser(username);
        return createResponse(ResponseEntity.ok(response));
    }

    //TODO Test용 지울 예정
    @GetMapping("/getUserDetail")
    @Secured({UserRole.Authority.HUB_ADMIN})
    public ResponseEntity<?> getUserDetail(@AuthenticationPrincipal CustomUserDetailsImpl customUserDetails) {
        if (customUserDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        return ResponseEntity.ok(customUserDetails);
    }

    public <T> ResponseEntity<T> createResponse(ResponseEntity<T> response) {
        HttpHeaders headers = new HttpHeaders(response.getHeaders());
        headers.add("Server-Port", serverPort);
        return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode());
    }
}




