package com.sparta.deliveryroute.application.service;

import com.sparta.deliveryroute.application.client.HubService;
import com.sparta.deliveryroute.application.event.CreateDeliveryEvent;
import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryEventService {

	private final HubService hubService;
	private final DeliveryRouteRepository deliveryRouteRepository;

	@Transactional
	public void CreateDeliveryRoutes(CreateDeliveryEvent event) {
		// event 를 DeliveryRoute 로 변환하여 저장
	}

}
