package com.sparta.order.application.service;

import com.sparta.order.application.dto.request.OrderCreateRequestDto;
import com.sparta.order.application.dto.request.OrderSearchRequestDto;
import com.sparta.order.application.dto.request.OrderUpdateRequestDto;
import com.sparta.order.application.dto.response.*;
import com.sparta.order.domain.client.CompanyClient;
import com.sparta.order.domain.client.ProductClient;
import com.sparta.order.domain.exception.Error;
import com.sparta.order.domain.exception.OrderException;
import com.sparta.order.domain.model.Order;
import com.sparta.order.domain.model.OrderStatus;
import com.sparta.order.domain.repository.OrderRepository;
import com.sparta.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CompanyClient companyClient;
    private final ProductClient productClient;

    //임시 변수 생성
    UUID productId = UUID.randomUUID();
    UUID recipientCompanyId = UUID.randomUUID();
    String productName = "potato";
    Integer productPrice = 100000;
    UUID userId = UUID.randomUUID();
    UUID deliveryId = UUID.randomUUID();
    UUID requestCompanyId = UUID.randomUUID();


    @Override
    public OrderResponseDto createOrder(OrderCreateRequestDto request) {
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



        return OrderResponseDto.builder()
                .deliveryId(deliveryId)
                .orderId(order.getId()).build();

    }

    @Override
    @Transactional
    public OrderResponseDto deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(Error.NOT_FOUND_ORDER)
        );

        order.deleteOrder(orderId);

        return OrderResponseDto.builder()
                .orderId(order.getId())
                .deliveryId(deliveryId)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDetailResponseDto getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderException(Error.NOT_FOUND_ORDER)
        );

        return OrderDetailResponseDto.builder()
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

    @Transactional(readOnly = true)
    @Override
    public PageResponseDto<OrderListResponseDto> getOrders(OrderSearchRequestDto requestDto) {
        Page<Order> orders = findOrders(requestDto);

        List<UUID> recipientCompanyIds = orders.map(Order::getRecipientCompanyId).stream().distinct().toList();
        List<CompanyResponseDto> recipientCompanies = companyClient.findCompaniesByIds(recipientCompanyIds);

        List<UUID> productIds = orders.map(Order::getProductId).stream().distinct().toList();
        List<ProductResponseDto> products = productClient.findProductsByIds(productIds);

        // 응답값 반환
        Map<UUID, CompanyResponseDto> recipientCompanyMap = recipientCompanies.stream().collect(Collectors.toMap(CompanyResponseDto::companyId, c -> c));
        Map<UUID, ProductResponseDto> productMap = products.stream().collect(Collectors.toMap(ProductResponseDto::productId, p -> p));

        Page<OrderListResponseDto> results = orders.map(order -> {
            CompanyResponseDto recipientCompany = recipientCompanyMap.get(order.getRecipientCompanyId());
            ProductResponseDto product = productMap.get(order.getProductId());
            return OrderListResponseDto.from(order, recipientCompany, product);
        });

        return PageResponseDto.of(results);
    }


    @Transactional
    @Override
    public OrderResponseDto updateOrder(UUID orderId, OrderUpdateRequestDto requestDto) {
        return null;
    }

    private Page<Order> findOrders(OrderSearchRequestDto requestDto) {
        if ("RECIPIENT_NAME".equals(requestDto.searchType())) {

            List<CompanyResponseDto> findCompanies = companyClient.findCompaniesByName(requestDto.searchValue());
            List<UUID> companyIds = findCompanies.stream().map(CompanyResponseDto::companyId).toList();

            // 업체 ID 리스트를 조건으로 필터링
            return orderRepository.searchOrdersByCompanyIds(requestDto, companyIds);
        }
        return orderRepository.searchOrders(requestDto);
    }
}
