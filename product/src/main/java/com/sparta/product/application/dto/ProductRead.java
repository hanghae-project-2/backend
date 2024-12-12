package com.sparta.product.application.dto;

import com.sparta.product.domain.model.Product;

import java.util.UUID;

public class ProductRead {

    public record Request(UUID productId) {
    }

    public record Response(String name, UUID companyId, Integer amount) {
        public static ProductRead.Response of(Product product) {
            return new ProductRead.Response(product.getName(), product.getCompanyId(), product.getAmount());
        }
    }

}
