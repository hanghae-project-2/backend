package com.sparta.delivery.application.event;

import lombok.Builder;

import java.util.UUID;


public record DeliveryRouteEvent(
        UUID deliveryId,
        UUID startHubId,
        UUID endHubId
) {
}
