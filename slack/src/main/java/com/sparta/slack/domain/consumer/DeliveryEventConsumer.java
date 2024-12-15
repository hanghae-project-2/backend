package com.sparta.slack.domain.consumer;

import com.sparta.slack.application.dto.SlackEvent;

public interface DeliveryEventConsumer {

    void consumeDeliveryEvent(SlackEvent event);

}
