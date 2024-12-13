package com.sparta.order.domain.service;

import com.sparta.order.application.dto.OrderCreate;
import com.sparta.order.application.dto.OrderDetail;
import com.sparta.order.application.dto.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(OrderCreate request);

    OrderResponse deleteOrder(UUID orderId);

    OrderDetail getOrderById(UUID orderId);
}
