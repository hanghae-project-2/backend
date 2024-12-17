package com.sparta.product.domain.consumer;

import com.sparta.product.application.event.ProductEvent;

public interface ProductEventConsumer {

    void consumeProductEvent(ProductEvent productEvent);
}
