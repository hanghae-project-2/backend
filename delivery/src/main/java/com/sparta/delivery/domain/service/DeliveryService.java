package com.sparta.delivery.domain.service;

import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.DeliveryListResponseDto;
import com.sparta.delivery.application.dto.response.DeliveryDetailResponseDto;
import com.sparta.delivery.domain.model.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    DeliveryDetailResponseDto getDeliveryById(UUID deliveryId);

    UUID createDelivery();

    UUID updateDelivery(UUID deliveryId, DeliveryStatus status);

    UUID deleteDelivery(UUID deliveryId);

    Page<DeliveryListResponseDto> getDeliveries(Pageable pageable, DeliverySearchRequestDto requestDto);

    UUID getDeliveryByOrderId(UUID orderId);

    UUID deleteDeliveryByOrderId(UUID orderId);
}
