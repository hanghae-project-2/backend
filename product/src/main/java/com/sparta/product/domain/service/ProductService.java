package com.sparta.product.domain.service;

import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductCreate.Response createProduct(ProductCreate.Request productCreate) {

        Product product = Product.createProduct(
                productCreate.name(),
                productCreate.companyId(),
                productCreate.amount());

        productRepository.save(product);

        return ProductCreate.Response.of(product);

    }



}
