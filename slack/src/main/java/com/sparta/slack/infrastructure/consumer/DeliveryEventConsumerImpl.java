package com.sparta.slack.infrastructure.consumer;

import com.sparta.slack.domain.service.SlackService;
import com.sparta.slack.infrastructure.dto.SlackEvent;
import com.sparta.slack.domain.consumer.DeliveryEventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class DeliveryEventConsumerImpl implements DeliveryEventConsumer {

    private final SlackService slackService;

    @Override
    @KafkaListener(topics = "created-delivery-event-slack", groupId = "slack-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeDeliveryEvent(SlackEvent event) {
        slackService.sendMessage(event);
    }

}
