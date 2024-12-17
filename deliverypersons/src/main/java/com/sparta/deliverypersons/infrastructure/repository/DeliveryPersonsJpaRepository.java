package com.sparta.deliverypersons.infrastructure.repository;

import com.sparta.deliverypersons.domain.model.DeliveryPersons;
import com.sparta.deliverypersons.domain.model.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryPersonsJpaRepository extends JpaRepository<DeliveryPersons, UUID> {

    long countByType(DeliveryType type);
    long countByTypeAndHubId(DeliveryType type, UUID hubId);
    @Query("SELECT COALESCE(MAX(dp.deliverySequence), 0) FROM DeliveryPersons dp WHERE dp.type = :type AND (:hubId IS NULL OR dp.hubId = :hubId)")
    Optional<Integer> findMaxSequenceByTypeAndHubId(@Param("type") DeliveryType type, @Param("hubId") UUID hubId);
    @Query(value = "SELECT id FROM delivery_persons ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<byte[]> findRandomDeliveryPersonId();
}
