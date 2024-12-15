package com.sparta.slack.application.consumer;

import com.sparta.slack.application.dto.SlackEvent;
import com.sparta.slack.domain.consumer.DeliveryEventConsumer;
import org.springframework.stereotype.Service;

@Service
public class DeliveryEventConsumerImpl implements DeliveryEventConsumer {

    @Override
    public void consumeDeliveryEvent(SlackEvent event) {

    }
}
