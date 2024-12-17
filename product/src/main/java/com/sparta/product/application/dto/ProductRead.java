package com.sparta.product.application.dto;

import com.sparta.product.domain.model.Product;

import java.util.UUID;

public class ProductRead {

    public record Response(UUID productId, String name, UUID companyId, Integer amount) {
        public static ProductRead.Response of(Product product) {
            return new ProductRead.Response(product.getId(),product.getName(), product.getCompanyId(), product.getAmount());
        }
    }

}
