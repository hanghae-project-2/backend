package com.sparta.order.application.dto.response;

import java.util.UUID;

public record ProductResponseDto(
        UUID productId,
        String productName
) {
}
