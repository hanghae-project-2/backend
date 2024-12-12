package com.sparta.order.domain.client;

import com.sparta.order.application.dto.response.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name="product-service")
public interface ProductClient {

    @PostMapping("/api/products/batch")
    List<ProductResponseDto> findProductsByIds(@RequestBody List<UUID> ids);

    @GetMapping("/api/products/{productId}")
    ProductResponseDto findProductById(@PathVariable("productId") UUID productId);

}
