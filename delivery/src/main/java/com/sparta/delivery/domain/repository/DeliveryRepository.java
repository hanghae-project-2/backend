package com.sparta.delivery.domain.repository;

import com.sparta.delivery.domain.model.Delivery;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {
    Optional<Delivery> findById(UUID deliveryId);

    Delivery save(Delivery delivery);
}
