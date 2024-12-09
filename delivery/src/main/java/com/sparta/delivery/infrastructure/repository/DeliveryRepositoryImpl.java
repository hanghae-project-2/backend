package com.sparta.delivery.infrastructure.repository;

import com.sparta.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {
    private final DeliveryJpaRepository deliveryJpaRepository;
}
