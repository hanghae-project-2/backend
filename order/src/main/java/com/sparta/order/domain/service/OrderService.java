package com.sparta.order.domain.service;

import com.sparta.order.application.dto.request.OrderCreateRequestDto;
import com.sparta.order.application.dto.request.OrderSearchRequestDto;
import com.sparta.order.application.dto.request.OrderUpdateRequestDto;
import com.sparta.order.application.dto.response.OrderDetailResponseDto;
import com.sparta.order.application.dto.response.OrderListResponseDto;
import com.sparta.order.application.dto.response.OrderResponseDto;
import com.sparta.order.application.dto.response.PageResponseDto;

import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateRequestDto requestDto);

    OrderResponseDto deleteOrder(UUID orderId);

    OrderDetailResponseDto getOrderById(UUID orderId);

    PageResponseDto<OrderListResponseDto> getOrders(OrderSearchRequestDto requestDto);

    OrderResponseDto updateOrder(UUID orderId, OrderUpdateRequestDto requestDto);
}
