package com.sparta.deliveryroute.application.service;

import com.sparta.deliveryroute.application.client.HubService;
import com.sparta.deliveryroute.application.dto.response.DeliveryRouteResponseDto;
import com.sparta.deliveryroute.application.dto.response.HubRouteResponseDto;
import com.sparta.deliveryroute.domain.model.DeliveryRoute;
import com.sparta.deliveryroute.domain.model.DeliveryStatus;
import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryRouteService {

	private final DeliveryRouteRepository deliverRouteRepository;
	private final HubService hubService;

	@Transactional
	public void deleteByDeliveryId(UUID userId, UUID deliveryId) {

		List<DeliveryRoute> deliveryRouteList = deliverRouteRepository.findByDeliveryId(deliveryId);

		deliveryRouteList.forEach(route -> route.markAsDelete(userId));

		deliverRouteRepository.saveAll(deliveryRouteList);
	}

	@Transactional
	@Scheduled(cron = "0 0 * * * *")
	public void updateByDeliveryStatus() {

		List<DeliveryRoute> deliveryRouteList = deliverRouteRepository.findAll();

		deliveryRouteList.forEach(route -> {
			if (route.getStatus().equals(DeliveryStatus.WAITING)) {
				route.updateStatus(DeliveryStatus.IN_TRANSIT);
			} else if (route.getStatus().equals(DeliveryStatus.IN_TRANSIT)) {
				route.updateStatus(DeliveryStatus.ARRIVED);
			}
		});

		deliverRouteRepository.saveAll(deliveryRouteList);
	}

	@Transactional(readOnly = true)
	public List<DeliveryRouteResponseDto> getDeliveryRoutesByDeliveryId(UUID id) {

		List<DeliveryRoute> deliveryRouteList = deliverRouteRepository.findByDeliveryId(id);

		Map<UUID, HubRouteResponseDto> hubRouteMap = hubService.findHubRoutesByHubRouteId(
				deliveryRouteList.stream().map(DeliveryRoute::getHubRouteId).toList()
		).stream().collect(Collectors.toMap(HubRouteResponseDto::hubRouteId, dto -> dto));

		return deliveryRouteList.stream().map(deliveryRoute -> {
					HubRouteResponseDto hubRoute = hubRouteMap.get(deliveryRoute.getHubRouteId());

					return new DeliveryRouteResponseDto(
							deliveryRoute.getId(),
							hubRoute.startHubId(),
							hubRoute.startHubName(),
							hubRoute.endHubId(),
							hubRoute.endHubName(),
							deliveryRoute.getSequence(),
							hubRoute.estimatedMeter(),
							hubRoute.estimatedSecond(),
							deliveryRoute.getActualDistance(),
							deliveryRoute.getActualTime(),
							deliveryRoute.getStatus().getKey()
					);
				}).sorted(Comparator.comparingInt(DeliveryRouteResponseDto::sequence))
				.toList();
	}
}
