package com.sparta.deliverypersons.application.event;

import com.sparta.deliverypersons.domain.model.DeliveryType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DeliveryEvent(
    UUID deliveryId,
    UUID userId,
    UUID hubId,
    DeliveryType type
) {
}
