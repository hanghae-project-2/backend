package com.sparta.product.presentation.api.controller;

import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.domain.service.ProductService;
import com.sparta.product.presentation.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductCreate.Response> createProduct(@RequestBody ProductCreate.Request productCreate) {
        ProductCreate.Response productResponse = productService.createProduct(productCreate);
        return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), productResponse);
    }

    @GetMapping
    public ApiResponse<?> getAllProducts() {

        return null;
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductRead.Response> getProductById(@PathVariable UUID productId) {
        ProductRead.Request productReadRequest = new ProductRead.Request(productId);
        ProductRead.Response productReadResponse = productService.getProduct(productReadRequest);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productReadResponse);

    }

    @PatchMapping("/{productId}")
    public ApiResponse<?> updateProductById(@PathVariable UUID productId) {

        return null;
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<?> deleteProductById(@PathVariable UUID productId) {

        return null;
    }


}
