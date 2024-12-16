package com.sparta.deliveryroute.presentation.api.controller;

import com.sparta.deliveryroute.application.service.DeliveryRouteService;
import com.sparta.deliveryroute.presentation.api.response.DeliveryRouteResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InternalDeliveryRouteController {

	private final DeliveryRouteService deliveryRouteService;

	@DeleteMapping("/delivery-route/deliveries/{deliveryId}")
	public void deleteByDeliveryId(
			@PathVariable UUID deliveryId,
			HttpServletRequest request
	) {
		deliveryRouteService.deleteByDeliveryId(request, deliveryId);
	}

	@GetMapping("/delivery-route/deliveries/{deliveryId}")
	public List<DeliveryRouteResponse> getDeliveryRoutesByDeliveryId(@PathVariable UUID deliveryId) {
		return DeliveryRouteResponse.from(deliveryRouteService.getDeliveryRoutesByDeliveryId(deliveryId));
	}

}
