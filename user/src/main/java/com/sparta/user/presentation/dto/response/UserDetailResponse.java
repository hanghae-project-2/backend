package com.sparta.user.presentation.dto.response;

import com.sparta.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserDetailResponse {
    private UUID id;
    private String username;
    private String slackId;
    private String role;
    private Boolean isApproved;

    public static UserDetailResponse from(User user) {
        return new UserDetailResponse(
                user.getId(),
                user.getUsername(),
                user.getSlackId(),
                user.getRole() != null ? user.getRole().name() : null,
                user.getIsApproved()
        );
    }
}

