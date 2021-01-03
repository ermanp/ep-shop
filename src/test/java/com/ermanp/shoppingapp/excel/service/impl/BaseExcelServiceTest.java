package com.ermanp.shoppingapp.excel.service.impl;

import com.ermanp.shoppingapp.product.domain.mock.MockData;
import junit.framework.TestCase;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BaseExcelServiceTest<T> extends TestCase {

    List<MockData> mockDataList = new ArrayList<>();


    @Test
    void extractFieldValues() {
    }

    @Test
    void getClassFieldByName() {
    }

    @Test
    void getClassFields() {
        mockDataList = Arrays.asList(new MockData("Erman","Payasli","ermanpayasli@gmail.com","Adana"));

      //  for(T item )
        assertTrue(CollectionUtils.isNotEmpty(mockDataList));
    }

    @Test
    void getDeclaredFields_test(){

    }

    void forClass(Class<T> clazz){
        for(Field field : clazz.getDeclaredFields()){

        }
    }
}