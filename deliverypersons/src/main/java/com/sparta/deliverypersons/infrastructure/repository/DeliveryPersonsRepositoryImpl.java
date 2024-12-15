package com.sparta.deliverypersons.infrastructure.repository;

import com.sparta.deliverypersons.domain.repository.DeliveryPersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryPersonsRepositoryImpl implements DeliveryPersonsRepository {

    private final DeliveryPersonsJpaRepository deliveryPersonsJpaRepository;

}
