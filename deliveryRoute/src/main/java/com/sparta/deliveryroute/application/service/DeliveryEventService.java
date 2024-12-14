package com.sparta.deliveryroute.application.service;

import com.sparta.deliveryroute.application.client.HubService;
import com.sparta.deliveryroute.application.dto.RouteResult;
import com.sparta.deliveryroute.application.event.CreateDeliveryEvent;
import com.sparta.deliveryroute.domain.model.DeliveryRoute;
import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import com.sparta.deliveryroute.infrastructure.redis.RedisService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryEventService {

	private final HubService hubService;
	private final DeliveryRouteRepository deliveryRouteRepository;
	private final RedisService redisService;

	@Transactional
	public void CreateDeliveryRoutes(CreateDeliveryEvent event) {
		RouteResult result = hubService.findHubRoutesById(event.startHubId(), event.endHubId());

		Iterator<String> iterator = result.path().iterator();
		String startHubName = iterator.next();

		List<DeliveryRoute> deliveryRoutes = new ArrayList<>();
		int sequence = 0;

		while (iterator.hasNext()) {
			String endHubName = iterator.next();

			RouteResult routeResult = redisService.getHash(startHubName, endHubName);

			DeliveryRoute deliveryRoute = DeliveryRoute.createDeliveryRoute(
					event.deliveryId(),
					redisService.getValue(startHubName, endHubName),
					sequence,
					routeResult.distance(),
					routeResult.time()
			);

			deliveryRoutes.add(deliveryRoute);

			startHubName = endHubName;
			sequence++;
		}

		deliveryRouteRepository.saveAll(deliveryRoutes);
	}

}
