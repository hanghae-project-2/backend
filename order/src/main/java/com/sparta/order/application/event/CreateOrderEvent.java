package com.sparta.order.application.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateOrderEvent(
        UUID orderId,
        UUID productId,
        Integer quantity
) {
}
