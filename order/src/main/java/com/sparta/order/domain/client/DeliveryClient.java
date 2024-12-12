package com.sparta.order.domain.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "delivery-client")
public interface DeliveryClient {
}
