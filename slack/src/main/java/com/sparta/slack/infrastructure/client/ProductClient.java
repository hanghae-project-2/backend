package com.sparta.slack.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {
}
