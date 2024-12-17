package com.sparta.deliveryroute.application.dto.response;

import java.util.UUID;

public record HubRouteResponseDto(
		UUID hubRouteId,
		UUID startHubId,
		String startHubName,
		UUID endHubId,
		String endHubName,
		Integer estimatedSecond,
		Integer estimatedMeter
) {
}
