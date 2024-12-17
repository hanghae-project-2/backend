package com.sparta.delivery.application.dto.response;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String username,
        UUID slackId,
        String role,
        Boolean isApproved) {
}
