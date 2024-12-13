package com.sparta.deliveryroute.domain.repository;

import com.sparta.deliveryroute.domain.model.DeliveryRoute;

import java.util.List;

public interface DeliveryRouteRepository {

	List<DeliveryRoute> saveAll(List<DeliveryRoute> deliveryRoutes);
}
