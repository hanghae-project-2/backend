package com.sparta.delivery.application.event;

import com.sparta.delivery.domain.model.Delivery;
import lombok.Builder;

import java.util.UUID;


public record CreateDeliveryEvent(
        UUID deliveryId,
        UUID startHubId,
        UUID endHubId,
        UUID deliveryPersonId,
        String deliveryAddress,
        UUID recipientSlackId,
        String recipientName,
        UUID orderId
) {
    public static CreateDeliveryEvent from(Delivery delivery, UUID orderId) {
        return new CreateDeliveryEvent(
                delivery.getId(),
                delivery.getStartHubId(),
                delivery.getEndHubId(),
                delivery.getDeliveryPersonId(),
                delivery.getDeliveryAddress(),
                delivery.getRecipientSlackId(),
                delivery.getRecipientName(),
                orderId
        );
    }
}
