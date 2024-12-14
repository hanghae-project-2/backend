package com.sparta.delivery.application.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateDeliveryEvent(
        UUID deliveryId,
        UUID startHubId,
        UUID endHubId
        //TODO : 추후 슬랙 추가
) {
}
