package com.sparta.slack.infrastructure.dto;

import java.util.UUID;

public record SlackEvent(UUID recipientSlackId,
                         UUID orderId,
                         String deliveryAddress,
                         UUID deliveryPersonId,
                         String recipientName,
                         UUID originHubId,
                         UUID destinationHubId) {
}


