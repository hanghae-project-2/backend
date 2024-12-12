package com.sparta.product.domain.service;

import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.application.dto.ProductDelete;
import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.application.dto.ProductUpdate;

import java.util.UUID;

public interface ProductService {

    ProductCreate.Response createProduct(ProductCreate.Request productCreate);

    ProductDelete.Response deleteProduct(UUID productId);

    ProductRead.Response getProduct(UUID productId);

    ProductUpdate.Response updateProduct(UUID productId, ProductUpdate.Request productUpdate);
}
