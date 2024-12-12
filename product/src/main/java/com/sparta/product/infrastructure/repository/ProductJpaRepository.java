package com.sparta.product.infrastructure.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.model.QProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.UUID;

public interface ProductJpaRepository
        extends JpaRepository<Product, UUID>,
ProductRepositoryCustom,
        QuerydslPredicateExecutor<Product>,
        QuerydslBinderCustomizer<QProduct> {

    @Override
    default void customize(QuerydslBindings bindings, QProduct root) {
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
        bindings.excludeUnlistedProperties(true);
    }
}
