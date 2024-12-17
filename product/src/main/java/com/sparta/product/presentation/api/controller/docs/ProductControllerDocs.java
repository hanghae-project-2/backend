package com.sparta.product.presentation.api.controller.docs;

import com.querydsl.core.types.Predicate;
import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.application.dto.ProductDelete;
import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.application.dto.ProductUpdate;
import com.sparta.product.domain.model.Product;
import com.sparta.product.presentation.api.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Product", description = "상품 API")
public abstract class ProductControllerDocs {

    @Operation(summary = "상품 생성", description = "상품 생성 API")
    @PostMapping("/products")
    public abstract ApiResponse<ProductCreate.Response> createProduct(@RequestBody ProductCreate.Request productCreate);

    @Operation(summary = "상품 목록 조회 및 검색", description = "상품 목록 조회 및 검색 API")
    @GetMapping("/products")
    public abstract ApiResponse<Page<Product>> getPagedProducts(
            @QuerydslPredicate(root = Product.class) Predicate predicate,
            @PageableDefault(sort = "id", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable
    );

    @Operation(summary = "상품 단건 조회", description = "상품 단건 조회 API")
    @GetMapping("/products/{productId}")
    public abstract ApiResponse<ProductRead.Response> getProductById(@PathVariable UUID productId);

    @Operation(summary = "상품 수정", description = "상품 수정 API")
    @PatchMapping("/products/{productId}")
    public abstract ApiResponse<ProductUpdate.Response> updateProductById(
            @PathVariable UUID productId,
            @RequestBody ProductUpdate.Request productUpdate
    );

    @Operation(summary = "상품 삭제", description = "상품 삭제 API")
    @DeleteMapping("/products/{productId}")
    public abstract ApiResponse<ProductDelete.Response> deleteProductById(@PathVariable UUID productId);
}

