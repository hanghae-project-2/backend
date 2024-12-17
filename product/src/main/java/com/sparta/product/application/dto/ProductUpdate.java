package com.sparta.product.application.dto;

import com.sparta.product.domain.model.Product;

import java.util.UUID;

public class ProductUpdate {

    public record Request(String name, UUID companyId, Integer amount){
    }

    public record Response(UUID productId, String name,Integer amount) {
        public static Response of(Product product) {
            return new Response(product.getId(), product.getName(),product.getAmount());
        }
    }

}
