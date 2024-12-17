package com.sparta.product.infrastructure.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.model.QProduct;
import com.sparta.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static com.sparta.product.domain.model.QProduct.product;
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(Product product) {
        productJpaRepository.save(product);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public Page<Product> getPagedResults(Predicate predicate, Pageable pageable) {

        // 페이징된 데이터 조회
        List<Product> products = queryFactory
                .select(product)
                .from(product)
                .where(predicate != null ? predicate : product.isNotNull()) // 기본 조건 처리
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 총 개수 계산
        Long total = queryFactory
                .select(product.count())
                .from(product)
                .where(predicate != null ? predicate : product.isNotNull()) // 기본 조건 처리
                .fetchOne();

        return new PageImpl<>(
                products,
                pageable,
                total != null ? total : 0
        );
    }

}
