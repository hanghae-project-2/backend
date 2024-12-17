package com.sparta.delivery.application.dto.response;

import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.model.DeliveryStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
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

    public static DeliveryListResponseDto from(Delivery delivery, HubResponseDto originHub, HubResponseDto destinationHub) {
        return DeliveryListResponseDto.builder()
                .deliveryId(delivery.getId())
                .orderId(delivery.getOrderId())
                .deliveryStatus(delivery.getCurrentStatus().toString())
                .originHubId(originHub.hubId())
                .originHubName(originHub.hubName())
                .destinationHubId(destinationHub.hubId())
                .destinationHubName(destinationHub.hubName())
                .recipientName(delivery.getRecipientName())
                .deliveryAddress(delivery.getDeliveryAddress())
                .createdAt(delivery.getCreatedAt())
                .updatedAt(delivery.getUpdatedAt())
                .build();
    }
}
