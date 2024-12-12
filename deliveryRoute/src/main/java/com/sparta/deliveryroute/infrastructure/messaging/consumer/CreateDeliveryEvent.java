package com.sparta.deliveryroute.infrastructure.messaging.consumer;

import java.util.UUID;

public record CreateDeliveryEvent(

		UUID deliveryId,

		UUID startHubId,

		UUID endHubId
) {
}
