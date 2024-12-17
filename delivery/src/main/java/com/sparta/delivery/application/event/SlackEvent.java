package com.sparta.delivery.application.event;

import java.util.UUID;


public record SlackEvent(
        UUID recipientSlackId,
        String deliveryAddress,
        UUID deliveryPersonId,
        String recipientName,
        UUID startHubId,
        UUID endHubId,
        UUID orderId,
        UUID createdBy
) {
}
