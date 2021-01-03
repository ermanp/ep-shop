package com.ermanp.shoppingapp.excel.service.impl;

import com.ermanp.shoppingapp.excel.output.ProductOutput;
import com.ermanp.shoppingapp.product.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProductMapper {

    public ProductOutput toOutput(Product product){
        return ProductOutput.builder()
                .name(product.getName())
                .code(product.getCode())
                .companyId(product.getCompanyId())
                .description(product.getDescription())
                .features(product.getDescription())
                .id(product.getId())
                .build();
    }
}
