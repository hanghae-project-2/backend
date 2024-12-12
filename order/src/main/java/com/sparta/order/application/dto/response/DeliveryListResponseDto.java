package com.sparta.order.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliveryListResponseDto(
        UUID deliveryId,
        UUID orderId,
        String deliveryStatus,
        UUID originHubId,
        String originHubName,
        UUID destinationHubId,
        String destinationHubName,
        String recipientName,
        String deliveryAddress,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
