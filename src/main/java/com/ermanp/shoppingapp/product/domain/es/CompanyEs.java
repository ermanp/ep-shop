package com.ermanp.shoppingapp.product.domain.es;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
@Data
@Builder
public class CompanyEs {
    private String id;
    private String name;
    private String code;
}
