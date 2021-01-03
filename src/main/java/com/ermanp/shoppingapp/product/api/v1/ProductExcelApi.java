package com.ermanp.shoppingapp.product.api.v1;

import com.ermanp.shoppingapp.excel.output.MockDataOutput;
import com.ermanp.shoppingapp.excel.output.ProductOutput;
import com.ermanp.shoppingapp.excel.service.ExcelService;
import com.ermanp.shoppingapp.excel.service.impl.BaseExcelService;
import com.ermanp.shoppingapp.excel.service.impl.ProductMapper;
import com.ermanp.shoppingapp.exception.ExcelExportException;
import com.ermanp.shoppingapp.product.domain.Product;
import com.ermanp.shoppingapp.product.domain.mock.MockData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/product/excel")
@RequiredArgsConstructor
public class ProductExcelApi {

    @Qualifier("mockDataExcelService")
    private final ExcelService<MockDataOutput> excelService;

    @Qualifier("productExcelService")
    private final ExcelService<ProductOutput> productExcelService;

    private final ProductMapper productMapper;

    @GetMapping(value = "/export/mock")
    public void exportMock(HttpServletResponse response) throws ExcelExportException {

        List<MockDataOutput> mockDataList = Arrays.asList(new MockDataOutput("Erman","Payasli","ermanpayasli@gmail.com","Adana"));

        Workbook workbook = excelService.convertWorkbook(mockDataList);

        sendWorkbook(response, workbook, "_mock");
    }

    @GetMapping(value = "/export/product")
    public void exportProduct(HttpServletResponse response) throws ExcelExportException {

        Product p1 = Product.builder()
                .id("1")
                .name("Toshiba")
                .code("01140")
                .description("Notebook")
                .companyId("TO001")
                .price(new BigDecimal(10))
                .features("Advanced")
                .categoryId("4")
                .build();

        Product p2 = Product.builder()
                .id("2")
                .name("Sony")
                .code("34394")
                .description("PlayStation")
                .companyId("SO034")
                .price(new BigDecimal(12))
                .features("Advanced")
                .categoryId("35")
                .build();

        List<Product> productList = Arrays.asList(p1,p2);


        Workbook workbook = productExcelService.convertWorkbook(productList.stream()
                .map(productMapper::toOutput)
                .collect(Collectors.toList()));

        sendWorkbook(response, workbook, "_product");
    }


    private void sendWorkbook(HttpServletResponse response, Workbook workbook, String xlsxName) throws ExcelExportException {
        String filename = String.format(
                "%s_%s.xlsx",
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()),
                xlsxName
        );

        response.setStatus(200);
        response.setContentType(BaseExcelService.CONTENT_TYPE_XLSX);
        response.setHeader("Content-disposition", "attachment; filename=" + filename);

        try {
            ServletOutputStream outputStream = response.getOutputStream();

            workbook.write(outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error(e);
            throw new ExcelExportException();
        }
    }
}
