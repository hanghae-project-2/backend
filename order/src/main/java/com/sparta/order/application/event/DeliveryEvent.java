package com.sparta.order.application.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DeliveryEvent(
        UUID orderId,
        String address,
        UUID startHubId,
        UUID endHubId
) {
}
