package com.sparta.deliveryroute.application.dto.response;

import java.util.UUID;

public record DeliveryRouteResponseDto(
		UUID deliveryRouteId,
		UUID startHubId,
		String startHubName,
		UUID endHubId,
		String endHubName,
		Integer sequence,
		Integer estimatedDistance,
		Integer estimatedDuration,
		Integer actualDistance,
		Integer actualDuration,
		String deliveryRouteStatus
) {
}
