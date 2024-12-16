package com.sparta.delivery.application.service;

import com.sparta.delivery.application.dto.response.UserResponseDto;
import com.sparta.delivery.application.event.CreateDeliveryEvent;
import com.sparta.delivery.application.event.DeleteEvent;
import com.sparta.delivery.application.event.DeliveryEvent;
import com.sparta.delivery.domain.exception.DeliveryException;
import com.sparta.delivery.domain.exception.Error;
import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.repository.DeliveryRepository;
import com.sparta.delivery.infrastructure.client.UserClient;
import com.sparta.delivery.infrastructure.message.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {
    private final DeliveryRepository deliveryRepository;
    private final KafkaProducer kafkaProducer;
    private final UserClient userClient;

    @Transactional
    public void createDelivery(DeliveryEvent event){

        //TODO : feignClient로 배송담당자, 유저이름, 슬랙 ID 받아오기
        UUID deliveryPersonId = UUID.randomUUID();
        UserResponseDto user = userClient.getUserById(UUID.randomUUID()).getData();

        Delivery delivery = deliveryRepository.save(
                Delivery.create(event, deliveryPersonId, user)
        );

        CreateDeliveryEvent cratedEvent = CreateDeliveryEvent.from(delivery, event.orderId());

        kafkaProducer.send(cratedEvent);

    }

    @Transactional
    public void deleteDelivery(DeleteEvent target) {
        Delivery delivery = deliveryRepository.findByOrderIdAndIsDeleteFalse(target.id()).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        //TODO : USERID 받으면 수정
        delivery.deleteDelivery(UUID.randomUUID());

        kafkaProducer.delete(target);
    }





}
