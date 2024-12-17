package com.sparta.product.infrastructure.repository;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.model.QProduct;
import jakarta.validation.constraints.NotNull;
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
    default void customize(QuerydslBindings bindings, @NotNull QProduct qProduct) {
        // name: 대소문자 구분 없이 포함 검색
        bindings.bind(qProduct.name).first(StringExpression::containsIgnoreCase);

        // amount: 특정 값 이상 검색
        bindings.bind(qProduct.amount).first(NumberPath::goe);

        // companyId: 정확히 일치하는 값 검색
        bindings.bind(qProduct.companyId).first(SimpleExpression::eq);

        // 기본적으로 나열되지 않은 속성은 제외
        bindings.excludeUnlistedProperties(true);
    }
}
