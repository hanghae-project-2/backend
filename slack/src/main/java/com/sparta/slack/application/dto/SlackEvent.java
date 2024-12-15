package com.sparta.slack.application.dto;

import java.util.UUID;

public record SlackEvent(UUID recipientSlackId,
                         String deliveryAddress,
                         UUID deliveryPersonId,
                         String recipientName,
                         UUID originHubId,
                         UUID destinationHubId) {
}


