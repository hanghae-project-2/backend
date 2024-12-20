package com.sparta.order.infrastructure.client;


import com.sparta.order.application.dto.response.DeliveryListResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "delivery-service")
public interface DeliveryClient {

    @GetMapping("/deliveries/orders/{orderId}")
    UUID getDeliveryByOrderId(@PathVariable UUID orderId);

}
