package com.sparta.delivery.application.dto.response;

import com.sparta.delivery.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliveryListResponseDto(
        UUID deliveryId,
        UUID orderId,
        DeliveryStatus deliveryStatus,
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
