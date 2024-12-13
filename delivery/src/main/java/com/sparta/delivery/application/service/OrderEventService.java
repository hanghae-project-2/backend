package com.sparta.delivery.application.service;

import com.sparta.delivery.application.event.CreateDeliveryEvent;
import com.sparta.delivery.application.event.DeliveryEvent;
import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.repository.DeliveryRepository;
import com.sparta.delivery.infrastructure.message.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {
    private final DeliveryRepository deliveryRepository;
    private final KafkaProducer kafkaProducer;

    @KafkaListener(topics = "created-order-event-delivery",
            properties = "spring.json.value.default.type=com.sparta.delivery.application.event.DeliveryEvent")
    public void createDelivery(DeliveryEvent event){

        //TODO: 로직 채우기, 생각이 안나 임시데이터 채워둘게요..

        CreateDeliveryEvent cratedEvent = CreateDeliveryEvent.builder()
                .deliveryId(UUID.randomUUID())
                        .endHubId(UUID.randomUUID())
                                .startHubId(UUID.randomUUID()).build();

        kafkaProducer.send(cratedEvent);


    }


}
