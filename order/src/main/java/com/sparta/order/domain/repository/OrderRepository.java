package com.sparta.order.domain.repository;

import com.sparta.order.application.dto.request.OrderSearchRequestDto;
import com.sparta.order.domain.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository{
    Order save(Order order);

    Optional<Order> findByIdAndIsDeleteFalse(UUID orderId);

    Page<Order> searchOrders(OrderSearchRequestDto requestDto);

    Page<Order> searchOrdersByCompanyIds(OrderSearchRequestDto requestDto, List<UUID> companyIds);

}
