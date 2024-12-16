package com.sparta.deliveryroute.infrastructure.repository;

import com.sparta.deliveryroute.domain.model.DeliveryRoute;
import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryRouteRepositoryImpl implements DeliveryRouteRepository {

	private final DeliveryRouteJpaRepository deliveryRouteJpaRepository;

	@Override
	public List<DeliveryRoute> saveAll(List<DeliveryRoute> deliveryRoutes) {
		return deliveryRouteJpaRepository.saveAll(deliveryRoutes);
	}

	@Override
	public List<DeliveryRoute> findByDeliveryId(UUID deliveryId) {
		return deliveryRouteJpaRepository.findByDeliveryId(deliveryId);
	}
}
