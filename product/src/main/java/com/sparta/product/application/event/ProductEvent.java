package com.sparta.product.application.event;

import java.util.UUID;

public record ProductEvent(
        UUID productId,
        Integer quantity
) {
}
