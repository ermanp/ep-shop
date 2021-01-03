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
public class MockDataOutput {

    @ExcelColumn
    private String name;
    @ExcelColumn
    private String surname;
    @ExcelColumn
    private String email;
    @ExcelColumn
    private String address;
}
