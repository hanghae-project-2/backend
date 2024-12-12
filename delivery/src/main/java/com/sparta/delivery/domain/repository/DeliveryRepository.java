package com.sparta.delivery.domain.repository;

import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.DeliveryListResponseDto;
import com.sparta.delivery.domain.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {
    Optional<Delivery> findById(UUID deliveryId);

    Delivery save(Delivery delivery);

    Page<DeliveryListResponseDto> getDeliveries(Pageable pageable, DeliverySearchRequestDto requestDto);
}