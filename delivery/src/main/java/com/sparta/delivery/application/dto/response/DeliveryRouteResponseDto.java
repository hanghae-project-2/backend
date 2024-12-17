package com.sparta.delivery.application.dto.response;

import java.util.UUID;


public record DeliveryRouteResponseDto(
        UUID deliveryRouteId,
        Integer sequence,
        UUID startHubId,
        String startHubName,
        UUID endHubId,
        String endHubName,//타입얘기
        Integer actualDistance,
        Integer actualDuration,//타입얘기
        String deliveryRouteStatus
) {
}
