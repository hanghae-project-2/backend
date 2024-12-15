package com.sparta.deliverypersons.infrastructure.repository;

import com.sparta.deliverypersons.domain.model.DeliveryPersons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DeliveryPersonsJpaRepository extends JpaRepository<DeliveryPersons, UUID> {

    @Query("SELECT COALESCE(MAX(dp.deliverySequence), 0) FROM DeliveryPersons dp")
    int findMaxSequence();
}
