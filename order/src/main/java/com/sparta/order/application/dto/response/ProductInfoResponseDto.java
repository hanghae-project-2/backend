package com.sparta.order.application.dto.response;

import java.util.UUID;

public record ProductInfoResponseDto(
        UUID productId,
        String name,
        UUID companyId,
        Integer amount
) {
}
