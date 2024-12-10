package com.sparta.order.infrastructure.repository;

import com.sparta.order.domain.model.Order;
import com.sparta.order.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {

}


