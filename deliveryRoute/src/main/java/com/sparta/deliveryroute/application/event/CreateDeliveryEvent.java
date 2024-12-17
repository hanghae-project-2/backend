package com.sparta.deliveryroute.application.event;

import java.util.UUID;

public record CreateDeliveryEvent(

		UUID deliveryId,

		UUID startHubId,

		UUID endHubId
) {
}
