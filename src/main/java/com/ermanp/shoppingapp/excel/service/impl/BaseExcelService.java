package com.ermanp.shoppingapp.excel.service.impl;

import com.ermanp.shoppingapp.excel.annotation.ExcelColumn;
import com.ermanp.shoppingapp.excel.service.ExcelService;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


public class BaseExcelService<T> implements ExcelService<T> {

    public static final String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Getter
    private Field[] classFields;

    @Getter
    private Map<String, String> fieldMappings = new LinkedHashMap<>();

    public BaseExcelService(Class<T> clazz) {
        getAnnotatedExcelColumns(clazz);
        //classFields = clazz.getDeclaredFields();

    }

    @Override
    public Workbook convertWorkbook(Collection<T> data) {
        Workbook workbook = new XSSFWorkbook(XSSFWorkbookType.XLSX);
        Sheet sheet = workbook.createSheet();
        appendRow(sheet,fieldMappings.values());
        for (T item : data) {
            appendRow(sheet, extractFieldValues(item));
        }
        return workbook;
    }

    private void appendRow(Sheet sheet, Iterable<String> columnValues) {
        Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
        int cellNo = 0;
        for (String columnValue : columnValues) {
            Cell cell = row.createCell(cellNo++);
            cell.setCellValue(columnValue);
        }
    }

    public void getAnnotatedExcelColumns(Class<?> clazz){
        classFields = clazz.getDeclaredFields();
        for (Field field : classFields) {
            field.setAccessible(true);
            ExcelColumn excelColumn = field.getDeclaredAnnotation(ExcelColumn.class);
            if(excelColumn == null){
                continue;
            }
            String mappingValue = excelColumn.name();
            if (!mappingValue.isEmpty()) {
                mappingValue = excelColumn.value();
            }

            if (mappingValue.isEmpty()) {
                mappingValue = field.getName();
            }

            fieldMappings.put(field.getName(), mappingValue);
        }
    }

    protected Iterable<String> extractFieldValues(T item){
        {
            return getFieldMappings().keySet()
                    .stream()
                    .map(this::getClassFieldByName)
                    .map(field -> {
                        Object value;

                        try {
                            value = field.get(item);
                        } catch (IllegalAccessException e) {
                            return "";
                        }

                        if (value == null) {
                            return "";
                        }

                        if (value instanceof List) {
                            return String.valueOf(((List) value).stream().map(String::valueOf).collect(Collectors.joining(",")));
                        }

                    /*if (value instanceof Date) {
                        return dateFormat.format(value);
                    }

                    if (value instanceof Calendar) {
                        return dateFormat.format(((Calendar) value).getTime());
                    }*/

                        return String.valueOf(value);
                    })
                    .collect(Collectors.toList());
        }
    }

    protected Field getClassFieldByName(String name) {
        return Arrays.stream(getClassFields()).filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
