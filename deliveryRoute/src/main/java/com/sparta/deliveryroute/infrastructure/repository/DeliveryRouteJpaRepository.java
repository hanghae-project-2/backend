package com.sparta.deliveryroute.infrastructure.repository;

import com.sparta.deliveryroute.domain.model.DeliveryRoute;
import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRouteJpaRepository extends JpaRepository<DeliveryRoute, Long>, DeliveryRouteRepository {
}
