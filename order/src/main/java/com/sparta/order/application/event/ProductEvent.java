package com.sparta.order.application.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductEvent(
        UUID productId,
        Integer quantity
) {
}
