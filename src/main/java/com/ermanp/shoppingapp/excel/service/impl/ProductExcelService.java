package com.ermanp.shoppingapp.excel.service.impl;

import com.ermanp.shoppingapp.excel.output.ProductOutput;

public class ProductExcelService extends BaseExcelService<ProductOutput> {
    public ProductExcelService(Class<ProductOutput> clazz) {
        super(clazz);
    }
}
