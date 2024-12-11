package com.sparta.delivery.domain.service;

import com.sparta.delivery.application.dto.DelieveryList;
import com.sparta.delivery.application.dto.DeliveryDetail;
import com.sparta.delivery.domain.model.DeliveryStatus;

import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    DeliveryDetail getDeliveryById(UUID deliveryId);

    UUID createDelivery();

    UUID updateDelivery(UUID deliveryId, DeliveryStatus status);

    UUID deleteDelivery(UUID deliveryId);

    List<DelieveryList> getDeliveryList();
}
