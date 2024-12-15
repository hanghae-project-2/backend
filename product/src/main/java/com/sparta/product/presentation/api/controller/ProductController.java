package com.sparta.product.presentation.api.controller;

import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.application.dto.ProductDelete;
import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.application.dto.ProductUpdate;
import com.sparta.product.domain.model.Product;
import com.sparta.product.application.service.ProductServiceImpl;
import com.sparta.product.domain.service.ProductService;
import com.sparta.product.presentation.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.function.Predicate;


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
    public ApiResponse<?> getAllProducts(
            @QuerydslPredicate(root = Product.class) Predicate predicate,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return null;
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductRead.Response> getProductById(@PathVariable UUID productId) {
        ProductRead.Response productReadResponse = productService.getProduct(productId);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productReadResponse);
    }

    @PatchMapping("/{productId}")
    public ApiResponse<ProductUpdate.Response> updateProductById(@PathVariable UUID productId,
                                                                 @RequestBody ProductUpdate.Request productUpdate) {
        ProductUpdate.Response productUpdateResponse = productService.updateProduct(productId,productUpdate);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productUpdateResponse);
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<ProductDelete.Response> deleteProductById(@PathVariable UUID productId) {
        ProductDelete.Response productDeleteResponse = productService.deleteProduct(productId);
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productDeleteResponse);
    }


}