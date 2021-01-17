package com.ermanp.shoppingapp.product.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "product")
@Data
@EqualsAndHashCode(of = "id")
@Builder
public class Product {

    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    private String companyId;
    private String features;
    private BigDecimal price;
    private String categoryId;
    private List<ProductImage> images;
    private Boolean active;

}
