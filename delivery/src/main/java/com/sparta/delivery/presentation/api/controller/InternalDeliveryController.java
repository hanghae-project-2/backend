package com.sparta.delivery.presentation.api.controller;

import com.sparta.delivery.domain.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InternalDeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("deliveries/orders/{orderId}")
    public UUID getDeliveryByOrderId(@PathVariable UUID orderId) {
        return deliveryService.getDeliveryByOrderId(orderId);
    }
}
