package com.sparta.product.application.dto;

import com.sparta.product.domain.model.Product;
import jakarta.persistence.Column;
import lombok.Getter;

import java.util.UUID;

public class ProductCreate {


    public record Request(String name, UUID companyId, Integer amount){
    }

    public record Response(UUID productId, String name) {
        public static Response of(Product product) {
                return new Response(product.getId(), product.getName());
        }
    }
}
