package com.sparta.order.application.event;

import com.sparta.order.application.dto.response.CompanyResponseDto;
import com.sparta.order.application.dto.response.ProductInfoResponseDto;
import com.sparta.order.domain.model.Order;
import lombok.Builder;

import java.util.UUID;


public record CreateOrderEvent(
        UUID orderId,
        UUID productId,
        Integer quantity,
        String address,
        UUID startHubId,
        UUID endHubId
) {
    public static CreateOrderEvent from(Order order, ProductInfoResponseDto product, CompanyResponseDto recipientCompany, CompanyResponseDto requestCompany) {
        return new CreateOrderEvent(
                order.getId(),
                product.productId(),
                order.getQuantity(),
                recipientCompany.address(),
                requestCompany.hubId(),
                recipientCompany.hubId()
        );
    }
}
