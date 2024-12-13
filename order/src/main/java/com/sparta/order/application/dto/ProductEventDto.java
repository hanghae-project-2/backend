package com.sparta.order.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductEventDto(
        UUID productId,
        Integer quantity
) {
}
