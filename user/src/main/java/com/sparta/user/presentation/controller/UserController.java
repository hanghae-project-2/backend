package com.sparta.user.presentation.controller;

import com.sparta.user.application.service.UserService;
import com.sparta.user.common.CustomException;
import com.sparta.user.common.ErrorCode;
import com.sparta.user.common.annotation.CheckUserStatus;
import com.sparta.user.domain.UserRole;
import com.sparta.user.infrastructure.security.CustomUserDetailsImpl;
import com.sparta.user.presentation.dto.request.UpdateUserRequest;
import com.sparta.user.presentation.dto.request.UpdateUserRoleRequest;
import com.sparta.user.presentation.dto.request.UserApprovalRequest;
import com.sparta.user.presentation.dto.response.UserResponse;
import com.sparta.user.presentation.response.Response;
import com.sparta.user.presentation.response.ResponseMessage;
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
    @CheckUserStatus
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
    @CheckUserStatus
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
    @CheckUserStatus
    public Response<String> approveUser(
            @PathVariable UUID userId,
            @RequestBody UserApprovalRequest request,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        userService.approveUser(userId, request.getRole(), userDetails.getUsername());

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ResponseMessage.USER_APPROVED_SUCCESS.getMessage()
        );
    }

    @PatchMapping("/{id}")
    @CheckUserStatus
    public Response<String> updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest request,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        if (!id.equals(userDetails.getId()) && !UserRole.MASTER.equals(userDetails.getRole())) {
            throw new CustomException(ErrorCode.SELF_OR_MASTER_ACCESS_REQUIRED);
        }

        userService.updateUser(id, request, userDetails.getUsername());

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ResponseMessage.USER_UPDATE_SUCCESS.getMessage()
        );
    }

    @PatchMapping("/{id}/role")
    @Secured(UserRole.Authority.MASTER)
    @CheckUserStatus
    public Response<String> updateUserRole(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRoleRequest request,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        if (!UserRole.MASTER.equals(userDetails.getRole())) {
            throw new CustomException(ErrorCode.MASTER_ACCESS_REQUIRED);
        }

        userService.updateUserRole(id, request.getRole(), userDetails.getUsername());

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ResponseMessage.ROLE_UPDATE_SUCCESS.getMessage()
        );
    }

    @DeleteMapping("/{id}")
    @CheckUserStatus
    public Response<String> deleteUser(
            @PathVariable UUID id,
            @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        userService.deleteUser(id, userDetails.getUsername());

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ResponseMessage.USER_DELETE_SUCCESS.getMessage()
        );
    }
}
