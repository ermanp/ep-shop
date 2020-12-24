package com.ermanp.shoppingapp.product.domain.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "ep-shopping",type = "category", shards=2)
public class CategoryEs {
    private String id;
    private String name;
    private String code;
}
