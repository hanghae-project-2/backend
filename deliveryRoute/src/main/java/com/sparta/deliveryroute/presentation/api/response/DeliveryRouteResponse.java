package com.sparta.deliveryroute.presentation.api.response;

import com.sparta.deliveryroute.application.dto.response.DeliveryRouteResponseDto;

import java.util.List;
import java.util.UUID;

public record DeliveryRouteResponse(
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

	public static DeliveryRouteResponse from(DeliveryRouteResponseDto responseDto) {
		return new DeliveryRouteResponse(
				responseDto.deliveryRouteId(),
				responseDto.startHubId(),
				responseDto.startHubName(),
				responseDto.endHubId(),
				responseDto.endHubName(),
				responseDto.sequence(),
				responseDto.estimatedDistance(),
				responseDto.estimatedDuration(),
				responseDto.actualDistance(),
				responseDto.actualDuration(),
				responseDto.deliveryRouteStatus()
		);
	}

	public static List<DeliveryRouteResponse> from(List<DeliveryRouteResponseDto> responseDtoList) {
		return responseDtoList.stream().map(DeliveryRouteResponse::from).toList();
	}

}
