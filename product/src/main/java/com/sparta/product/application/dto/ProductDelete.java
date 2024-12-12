package com.sparta.product.application.dto;

import com.sparta.product.domain.model.Product;
import lombok.Getter;

import java.util.UUID;

public class ProductDelete {

    public record Response(UUID id, String name) {
        public static Response of(Product product) {
            return new Response(product.getId(), product.getName());
        }
    }

}
