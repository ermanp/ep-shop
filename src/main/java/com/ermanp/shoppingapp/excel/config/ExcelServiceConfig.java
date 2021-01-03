package com.ermanp.shoppingapp.excel.config;

import com.ermanp.shoppingapp.excel.output.MockDataOutput;
import com.ermanp.shoppingapp.excel.output.ProductOutput;
import com.ermanp.shoppingapp.excel.service.impl.MockDataExcelService;
import com.ermanp.shoppingapp.excel.service.impl.ProductExcelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelServiceConfig {

    @Bean
    public MockDataExcelService mockDataExcelService(){
        return new MockDataExcelService(MockDataOutput.class);
    }

    @Bean
    public ProductExcelService productExcelService(){
        return new ProductExcelService(ProductOutput.class);
    }

}
