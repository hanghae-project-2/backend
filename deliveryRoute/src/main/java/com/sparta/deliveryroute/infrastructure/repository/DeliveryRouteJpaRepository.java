package com.sparta.deliveryroute.infrastructure.repository;

import com.sparta.deliveryroute.domain.model.DeliveryRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryRouteJpaRepository extends JpaRepository<DeliveryRoute, UUID> {

	List<DeliveryRoute> findByDeliveryId(UUID deliveryId);

}
