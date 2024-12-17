package com.sparta.delivery.application.dto.response;

import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.model.DeliveryStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record DeliveryDetailResponseDto(
        UUID deliveryId,
        UUID orderId,
        String deliveryStatus,
        UUID originHubId,
        String originHubName,
        UUID destinationHubId,
        String destinationHubName,
        String recipientName,
        String deliveryAddress,
        List<DeliveryRouteResponseDto> deliveryRoutes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static DeliveryDetailResponseDto from(Delivery delivery, HubResponseDto originHub, HubResponseDto destinationHub, List<DeliveryRouteResponseDto> deliveryRoutes) {
        return DeliveryDetailResponseDto.builder()
                .deliveryId(delivery.getId())
                .orderId(delivery.getOrderId())
                .deliveryStatus(delivery.getCurrentStatus().toString())
                .originHubId(originHub.hubId())
                .originHubName(originHub.hubName())
                .destinationHubId(destinationHub.hubId())
                .destinationHubName(destinationHub.hubName())
                .recipientName(delivery.getRecipientName())
                .deliveryAddress(delivery.getDeliveryAddress())
                .deliveryRoutes(deliveryRoutes)
                .createdAt(delivery.getCreatedAt())
                .updatedAt(delivery.getUpdatedAt())
                .build();
    }
}
