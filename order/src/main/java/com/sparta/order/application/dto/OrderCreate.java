package com.sparta.order.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class OrderCreate {
    private UUID recipientCompanyId;
    private UUID productId;
    private Integer quantity;
    private String specialRequests;
}
