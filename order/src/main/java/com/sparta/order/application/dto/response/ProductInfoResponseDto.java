package com.sparta.order.application.dto.response;

import java.util.UUID;

public record ProductInfoResponseDto(
        UUID productId,
        String productName,
        UUID requestCompanyId
) {
}
