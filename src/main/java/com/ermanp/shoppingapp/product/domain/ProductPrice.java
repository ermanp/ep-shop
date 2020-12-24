package com.ermanp.shoppingapp.product.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_price")
@Data
@EqualsAndHashCode(of = "id")
public class ProductPrice {
    private String id;
    private String productId;
    private MoneyTypes moneyType;
    private String price;

}
