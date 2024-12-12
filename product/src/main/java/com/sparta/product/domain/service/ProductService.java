package com.sparta.product.domain.service;

import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.application.dto.ProductDelete;
import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.domain.exception.ProductNullPointerException;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public ProductDelete.Response deleteProduct(ProductDelete.Request productDelete) {

        return null;
    }

    public ProductRead.Response getProduct(UUID productId) {
        return ProductRead.Response.of(productRepository
                .findById(productId)
                .orElseThrow(ProductNullPointerException::new));
    }


}
