package com.sparta.product.domain.service;

import com.querydsl.core.types.Predicate;
import com.sparta.product.application.dto.*;
import com.sparta.product.application.event.ProductEvent;
import com.sparta.product.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    ProductCreate.Response createProduct(ProductCreate.Request productCreate);

    ProductDelete.Response deleteProduct(UUID productId);

    ProductRead.Response getProduct(UUID productId);

    ProductUpdate.Response updateProduct(UUID productId, ProductUpdate.Request productUpdate);

    Page<Product> getProducts(Predicate predicate, Pageable pageable);

    void subtractAmount(ProductEvent productEvent);
}
