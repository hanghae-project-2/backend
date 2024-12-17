package com.sparta.delivery.domain.service;

import com.sparta.delivery.application.dto.request.DeliveryComplateRequestDto;
import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.DeliveryListResponseDto;
import com.sparta.delivery.application.dto.response.DeliveryDetailResponseDto;
import com.sparta.delivery.application.dto.response.PageResponseDto;
import com.sparta.delivery.domain.model.DeliveryStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DeliveryService {
    DeliveryDetailResponseDto getDeliveryById(UUID deliveryId, HttpServletRequest servletRequest);

    UUID updateDelivery(UUID deliveryId, DeliveryStatus status, HttpServletRequest servletRequest);

    UUID deleteDelivery(UUID deliveryId, HttpServletRequest servletRequest);

    PageResponseDto<DeliveryListResponseDto> getDeliveries(DeliverySearchRequestDto requestDto, HttpServletRequest servletRequest);

    UUID getDeliveryByOrderId(UUID orderId);

    UUID complateDelivery(UUID deliveryId, DeliveryComplateRequestDto requestDto, HttpServletRequest servletRequest);
}
