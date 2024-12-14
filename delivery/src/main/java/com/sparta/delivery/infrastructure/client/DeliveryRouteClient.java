package com.sparta.delivery.infrastructure.client;

import com.sparta.delivery.application.dto.response.DeliveryRouteResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "delivery-route-server")
public interface DeliveryRouteClient {
	@DeleteMapping("/delivery-route/deliveries/{deliveryId}")
	void deleteByDeliveryId(@PathVariable UUID deliveryId);

	@GetMapping("/delivery-route/deliveries/{deliveryId}")
	List<DeliveryRouteResponseDto> getDeliveryRoutesByDeliveryId(@PathVariable UUID deliveryId);
}
