package com.sparta.delivery.application.service;

import com.sparta.delivery.application.dto.request.CreateOrderEventDto;
import com.sparta.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {
    private final DeliveryRepository deliveryRepository;

    @KafkaListener(topics = "created-order-event",
            properties = "spring.json.value.default.type=com.sparta.delivery.application.event.CreateOrderEventDto")
    public void createDelivery(CreateOrderEventDto event){

        log.info(event.toString());
    }


}
