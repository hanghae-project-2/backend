package com.sparta.delivery.application.dto;

import java.util.UUID;


public record DeliveryRoute(
        UUID deliveryRouteId,
        String deliveryManagetName,
        Integer sequence,
        UUID startHubId,
        String startHubName,
        UUID endHubId,
        String endHubName,
        Double estimatedDistance,
        String estimatedDuration,//타입얘기
        Double actualDistance,
        String actualDuration,//타입얘기
        String deliveryRouteStatus
) {
}
