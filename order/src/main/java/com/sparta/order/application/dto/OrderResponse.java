package com.sparta.order.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class OrderResponse {
    private UUID orderId;
    private UUID deliveryId;
    private String message;

}
