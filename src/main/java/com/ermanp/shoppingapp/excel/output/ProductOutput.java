package com.ermanp.shoppingapp.excel.output;

import com.ermanp.shoppingapp.excel.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutput {

    @ExcelColumn
    private String id;
    @ExcelColumn
    private String name;
    @ExcelColumn
    private String code;
    @ExcelColumn
    private String description;
    @ExcelColumn
    private String companyId;
    @ExcelColumn
    private String features;
}
