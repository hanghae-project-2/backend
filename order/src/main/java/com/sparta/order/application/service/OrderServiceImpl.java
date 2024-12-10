package com.sparta.order.application.service;

import com.sparta.order.application.dto.OrderCreate;
import com.sparta.order.application.dto.OrderDetail;
import com.sparta.order.application.dto.OrderResponse;
import com.sparta.order.domain.exception.Error;
import com.sparta.order.domain.exception.OrderException;
import com.sparta.order.domain.model.Order;
import com.sparta.order.domain.model.OrderStatus;
import com.sparta.order.domain.repository.OrderRepository;
import com.sparta.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    //임시 변수 생성
    UUID productId = UUID.randomUUID();
    UUID recipientCompanyId = UUID.randomUUID();
    String productName = "potato";
    Integer productPrice = 100000;
    UUID userId = UUID.randomUUID();
    UUID deliveryId = UUID.randomUUID();
    UUID requestCompanyId = UUID.randomUUID();


    @Override
    public OrderResponse createOrder(OrderCreate request) {
        Order order = orderRepository.save(
            Order.builder()
                    .productId(productId)
                    .status(OrderStatus.RECEIVED)
                    .totalPrice(productPrice* request.quantity())
                    .specialRequests(request.specialRequests())
                    .quantity(request.quantity())
                    .price(productPrice)
                    .recipientCompanyId(recipientCompanyId)
                    .build()
        );



        return OrderResponse.builder()
                .deliveryId(deliveryId)
                .orderId(order.getId()).build();

    }

    @Override
    @Transactional
    public OrderResponse deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(Error.NOT_FOUND_ORDER)
        );

        order.deleteOrder(orderId);

        return OrderResponse.builder()
                .orderId(order.getId())
                .deliveryId(deliveryId)
                .build();
    }

    @Override
    public OrderDetail getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(Error.NOT_FOUND_ORDER)
        );

        return OrderDetail.builder()
                .orderId(orderId)
                .productId(order.getProductId())
                .requestCompanyId(requestCompanyId)
                .recipientCompanyName("gamgamjashop")
                .recipientCompanyId(order.getRecipientCompanyId())
                .requestCompanyName("gamjashop")
                .recipientCompanyId(order.getProductId())
                .productName(productName)
                .quantity(order.getQuantity())
                .deliveryId(deliveryId)
                .orderStatus(order.getStatus())
                .specialRequests(order.getSpecialRequests())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
