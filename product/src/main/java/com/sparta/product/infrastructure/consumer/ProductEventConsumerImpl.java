package com.sparta.product.infrastructure.consumer;

import com.sparta.product.application.event.ProductEvent;
import com.sparta.product.domain.consumer.ProductEventConsumer;
import com.sparta.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventConsumerImpl implements ProductEventConsumer {

    private final ProductService productService;

    @Override
    @KafkaListener(topics = "created-order-event-product", groupId = "slack-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeProductEvent(ProductEvent event) {
        productService.subtractAmount(event);
    }

}
