package com.sparta.user.presentation.controller;

import com.sparta.user.application.service.UserService;
import com.sparta.user.common.CustomException;
import com.sparta.user.domain.UserRole;
import com.sparta.user.infrastructure.security.CustomUserDetailsImpl;
import com.sparta.user.presentation.dto.request.UpdateUserRequest;
import com.sparta.user.presentation.dto.request.UpdateUserRoleRequest;
import com.sparta.user.presentation.dto.request.UserApprovalRequest;
import com.sparta.user.presentation.dto.response.UserResponse;
import com.sparta.user.presentation.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @RequestBody UserApprovalRequest request,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        String updatedBy = userDetails.getUsername();

        userService.approveUser(userId, request.getRole(), updatedBy);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "승인 및 권한이 부여되었습니다."
        );
    }

    @PatchMapping("/{id}")
    public Response<String> updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest request,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        if (!id.equals(userDetails.getId()) && !UserRole.MASTER.equals(userDetails.getRole())) {
            throw new CustomException("자신의 정보 또는 MASTER 권한만 수정할 수 있습니다.", HttpStatus.FORBIDDEN);
        }

        String updatedBy = userDetails.getUsername();

        userService.updateUser(id, request, updatedBy);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "회원 정보 수정 성공."
        );
    }

    @PatchMapping("/{id}/role")
    @Secured(UserRole.Authority.MASTER)
    public Response<String> updateUserRole(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRoleRequest request,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        if (!UserRole.MASTER.equals(userDetails.getRole())) {
            throw new CustomException("MASTER 권한이 있어야 이 작업을 수행할 수 있습니다.", HttpStatus.FORBIDDEN);
        }

        String updatedBy = userDetails.getUsername();

        userService.updateUserRole(id, request.getRole(), updatedBy);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "권한 수정 성공."
        );
    }
}
