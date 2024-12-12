package com.sparta.product.domain.repository;

import com.sparta.product.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(UUID id);
}
