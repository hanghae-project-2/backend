package com.sparta.delivery.application.service;

import com.sparta.delivery.application.dto.DelieveryList;
import com.sparta.delivery.application.dto.DeliveryDetail;
import com.sparta.delivery.domain.exception.DeliveryException;
import com.sparta.delivery.domain.exception.Error;
import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.model.DeliveryStatus;
import com.sparta.delivery.domain.repository.DeliveryRepository;
import com.sparta.delivery.domain.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryDetail getDeliveryById(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        return DeliveryDetail.fromEntity(delivery);
    }

    @Override
    public UUID createDelivery() {
        return null;
    }

    @Override
    @Transactional
    public UUID updateDelivery(UUID deliveryId, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        delivery.updateDelivery(status);

        return deliveryId;
    }

    @Override
    @Transactional
    public UUID deleteDelivery(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        delivery.deleteDelivery(delivery.getCreatedBy());

        return deliveryId;
    }

    @Override
    public List<DelieveryList> getDeliveryList() {
        return List.of();
    }
}
