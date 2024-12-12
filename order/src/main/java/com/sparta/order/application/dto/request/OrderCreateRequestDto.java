package com.sparta.order.application.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderCreateRequestDto(
        UUID recipientCompanyId,
        UUID productId,
        Integer quantity,
        String specialRequests) {

}
