package com.sparta.product.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    private Product(String name, UUID companyId, Integer amount) {
        this.name = name;
        this.companyId = companyId;
        this.amount = amount;
    }

    public static Product createProduct(String name, UUID companyId, Integer amount) {
        return new Product(name,companyId,amount);
    }

    private Product(UUID id, String name, UUID companyId, Integer amount) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.amount = amount;
    }

    public static Product updateProduct(UUID id, String name, UUID companyId, Integer amount) {
        return new Product(id,name,companyId,amount);
    }

    public void deleteProduct(UUID deletedBy) {
        super.delete(deletedBy);
    }
}
