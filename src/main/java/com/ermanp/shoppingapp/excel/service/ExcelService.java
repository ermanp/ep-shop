package com.ermanp.shoppingapp.excel.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Collection;

public interface ExcelService<T> {

    Workbook convertWorkbook(Collection<T> data);
}
