package com.sparta.product.presentation.api.controller;

import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.service.ProductService;
import com.sparta.product.presentation.api.response.Response;
import jdk.jshell.Snippet;
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
    public Response<ProductCreate.Response> createProduct(@RequestBody ProductCreate.Request productCreate) {
        ProductCreate.Response productResponse = productService.createProduct(productCreate);
        return new Response<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                productResponse
        );
    }

    @GetMapping
    public Response<?> getAllProducts() {

        return null;
    }

    @GetMapping("/{productId}")
    public Response<?> getProductById(@PathVariable UUID productId) {

        return null;
    }

    @PatchMapping("/{productId}")
    public Response<?> updateProductById(@PathVariable UUID productId) {

        return null;
    }

    @DeleteMapping("/{productId}")
    public Response<?> deleteProductById(@PathVariable UUID productId) {

        return null;
    }



}
