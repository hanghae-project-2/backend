package com.sparta.slack.infrastructure.client;

import com.sparta.slack.application.dto.OrderDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {

    @GetMapping("/orders/{orderId}")
    OrderDetails.Response getOrderById(@PathVariable UUID orderId);

}
