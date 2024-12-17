package com.sparta.delivery.domain.service;

import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.DeliveryListResponseDto;
import com.sparta.delivery.application.dto.response.DeliveryDetailResponseDto;
import com.sparta.delivery.application.dto.response.PageResponseDto;
import com.sparta.delivery.domain.model.DeliveryStatus;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DeliveryService {
    DeliveryDetailResponseDto getDeliveryById(UUID deliveryId);

    UUID updateDelivery(UUID deliveryId, DeliveryStatus status);

    UUID deleteDelivery(UUID deliveryId);

    PageResponseDto<DeliveryListResponseDto> getDeliveries(DeliverySearchRequestDto requestDto);

    UUID getDeliveryByOrderId(UUID orderId);

}
