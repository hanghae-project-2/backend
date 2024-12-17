package com.sparta.user.presentation.controller;

import com.sparta.user.application.service.AuthService;
import com.sparta.user.presentation.dto.response.AuthResponse;
import com.sparta.user.presentation.dto.request.SignInRequest;
import com.sparta.user.presentation.dto.request.SignUpRequest;
import com.sparta.user.presentation.response.Response;
import com.sparta.user.presentation.response.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public Response<AuthResponse> signIn(
            final @RequestBody SignInRequest request) {

        final AuthResponse response = authService.signIn(request);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }

    @PostMapping("/signOut")
    public Response<String> logout(
            HttpServletRequest request) {

        authService.logout(request);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ResponseMessage.LOGOUT_SUCCESS.getMessage()
        );
    }

    @PostMapping("/signUp")
    public Response<String> signUp(
            @RequestBody @Valid SignUpRequest request) {

        authService.createUser(request);

        return new Response<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                ResponseMessage.SIGN_UP_SUCCESS.getMessage()
        );
    }

    @GetMapping("/verify")
    public Response<Boolean> verifyUser(
            final @RequestParam(value = "username") String username) {

        Boolean response = authService.verifyUser(username);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }
}