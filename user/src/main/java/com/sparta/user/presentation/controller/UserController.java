package com.sparta.user.presentation.controller;

import com.sparta.user.application.service.UserService;
import com.sparta.user.domain.UserRole;
import com.sparta.user.presentation.dto.request.UserApprovalRequest;
import com.sparta.user.presentation.dto.response.UserResponse;
import com.sparta.user.presentation.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Response<UserResponse> getUserById(@PathVariable UUID id) {

        UserResponse user = userService.getUserById(id);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                user
        );
    }

    @GetMapping
    @Secured(UserRole.Authority.MASTER)
    public Response<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<UserResponse> users = userService.getAllUsers(page, size);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                users
        );
    }

    @PatchMapping("/{userId}/approval")
    @Secured(UserRole.Authority.MASTER)
    public Response<String> approveUser(
            @PathVariable UUID userId,
            @RequestBody UserApprovalRequest request) {

        userService.approveUser(userId, request.getRole());

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                null
        );
    }
}
