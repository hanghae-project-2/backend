package com.sparta.deliveryroute.domain.event;

import java.util.UUID;

public record CreateDeliveryEvent(

		UUID deliveryId,

		UUID startHubId,

		UUID endHubId
) {
}
