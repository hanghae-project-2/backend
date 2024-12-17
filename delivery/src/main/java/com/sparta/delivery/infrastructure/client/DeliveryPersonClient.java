package com.sparta.delivery.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "delivery-person-service")
public interface DeliveryPersonClient {
    @GetMapping("/delivery-persons/feign")
    UUID getDeliveryPersonFeignClient();

}
