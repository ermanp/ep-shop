package com.ermanp.shoppingapp.product.domain.es;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@Document(indexName = "ep-shopping",type = "product", shards=2)
public class ProductEs {
    private String id;
    private String name;
    private String code;
    private String description;
    private CompanyEs seller;
    private String features;
    private BigDecimal price;
    private CategoryEs category;
    private Boolean active;
}
