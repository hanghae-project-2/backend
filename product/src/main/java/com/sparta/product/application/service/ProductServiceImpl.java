package com.sparta.product.application.service;

import com.querydsl.core.types.Predicate;
import com.sparta.product.application.dto.ProductCreate;
import com.sparta.product.application.dto.ProductDelete;
import com.sparta.product.application.dto.ProductRead;
import com.sparta.product.application.dto.ProductUpdate;
import com.sparta.product.application.event.ProductEvent;
import com.sparta.product.domain.exception.ProductNullPointerException;
import com.sparta.product.domain.model.Product;
import com.sparta.product.domain.repository.ProductRepository;
import com.sparta.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductCreate.Response createProduct(ProductCreate.Request productCreate) {

        Product product = Product.createProduct(
                productCreate.name(),
                productCreate.companyId(),
                productCreate.amount());

        productRepository.save(product);

        return ProductCreate.Response.of(product);

    }

    @Override
    @Transactional
    public ProductDelete.Response deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNullPointerException::new);
        //TODO 유저 받으면 유저 ID 기입하기
        product.deleteProduct(UUID.randomUUID());
        return ProductDelete.Response.of(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductRead.Response getProduct(UUID productId) {
        return ProductRead.Response.of(productRepository
                .findById(productId)
                .orElseThrow(ProductNullPointerException::new));
    }

    @Override
    @Transactional
    public ProductUpdate.Response updateProduct(UUID productId, ProductUpdate.Request productUpdate) {
        Product product = Product.updateProduct(productId, productUpdate.name(), productUpdate.companyId(), productUpdate.amount());
        productRepository.save(product);
        return ProductUpdate.Response.of(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(Predicate predicate, Pageable pageable) {
        return productRepository.getPagedResults(predicate, pageable);
    }

    @Override
    @Transactional
    public void subtractAmount(ProductEvent productEvent) {
        Product product = productRepository.findById(productEvent.productId()).orElseThrow(ProductNullPointerException::new);
        Product updatedProduct = Product.updateProduct(product.getId(), product.getName(), product.getCompanyId(), product.getAmount() - productEvent.quantity());
        productRepository.save(updatedProduct);

    }


}
