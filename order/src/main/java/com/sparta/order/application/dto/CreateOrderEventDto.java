package com.sparta.order.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateOrderEventDto(
        UUID orderId,
        UUID productId,
        Integer quantity
) {
}
