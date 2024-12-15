package com.sparta.deliverypersons.infrastructure.messaging.consumer;

import com.sparta.deliverypersons.application.event.DeliveryEvent;
import com.sparta.deliverypersons.application.service.DeliveryPersonsService;
import com.sparta.deliverypersons.domain.model.DeliveryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryEventConsumer {

    private static final String CREATED_DELIVERY_EVENT = "created-delivery-event";

    private final DeliveryPersonsService deliveryPersonsService;

    @KafkaListener(topics = CREATED_DELIVERY_EVENT, groupId = "delivery_group")
    public void consume(UUID deliveryId) {

        deliveryPersonsService.createDeliveryPerson(deliveryId);
    }
}
