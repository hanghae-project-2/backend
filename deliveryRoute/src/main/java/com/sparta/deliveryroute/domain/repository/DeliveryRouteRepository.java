package com.sparta.deliveryroute.domain.repository;

import com.sparta.deliveryroute.domain.model.DeliveryRoute;

import java.util.List;
import java.util.UUID;

public interface DeliveryRouteRepository {

	List<DeliveryRoute> saveAll(List<DeliveryRoute> deliveryRoutes);

	List<DeliveryRoute> findByDeliveryId(UUID deliveryId);
}
