package com.sparta.product.domain.repository;

import com.querydsl.core.types.Predicate;
import com.sparta.product.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(UUID id);

    Page<Product> getPagedResults(Predicate predicate, Pageable pageable);
}
