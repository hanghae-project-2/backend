package com.sparta.product.presentation.api.controller;

import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.domain.service.ProductService;
import com.sparta.product.presentation.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InternalProductController {

    private final ProductService productService;

    @GetMapping("/products/{productId}")
    public ProductRead.Response getProductById(@PathVariable UUID productId) {
        return productService.getProduct(productId);
    }
}
