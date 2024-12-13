package com.sparta.delivery.application.dto.response;

import java.util.UUID;

public record HubResponseDto(
        UUID hubId,
        String hubName
) {
}
