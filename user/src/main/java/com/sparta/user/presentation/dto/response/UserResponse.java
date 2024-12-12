package com.sparta.user.presentation.dto.response;

import com.sparta.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private String slackId;
    private String role;
    private Boolean isApproved;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getSlackId(),
                user.getRole() != null ? user.getRole().name() : null,
                user.getIsApproved()
        );
    }
}

