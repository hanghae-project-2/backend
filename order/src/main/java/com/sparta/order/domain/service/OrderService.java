package com.sparta.order.domain.service;

import com.sparta.order.application.dto.request.OrderCreateRequestDto;
import com.sparta.order.application.dto.request.OrderSearchRequestDto;
import com.sparta.order.application.dto.request.OrderUpdateRequestDto;
import com.sparta.order.application.dto.response.OrderDetailResponseDto;
import com.sparta.order.application.dto.response.OrderListResponseDto;
import com.sparta.order.application.dto.response.OrderResponseDto;
import com.sparta.order.application.dto.response.PageResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateRequestDto requestDto, HttpServletRequest servletRequest);

    UUID deleteOrder(UUID orderId, HttpServletRequest servletRequest);

    OrderDetailResponseDto getOrderById(UUID orderId, HttpServletRequest servletRequest);

    PageResponseDto<OrderListResponseDto> getOrders(OrderSearchRequestDto requestDto, HttpServletRequest servletRequest);

    OrderResponseDto updateOrder(UUID orderId, OrderUpdateRequestDto requestDto, HttpServletRequest servletRequest);
}
