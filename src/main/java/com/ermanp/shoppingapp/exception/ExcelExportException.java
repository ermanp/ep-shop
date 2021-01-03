package com.ermanp.shoppingapp.exception;

public class ExcelExportException extends Exception{
    public ExcelExportException(){
        super("Could not perform the export operation due to an unknown error.");
    }
}
