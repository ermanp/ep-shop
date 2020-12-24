package com.ermanp.shoppingapp.product.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductDeliveryService {

    public String getDeliveryInfo(String productId){
        // TODO
        return "Tomorrow";
    }

    public boolean checkFreeDelivery(String productId, BigDecimal price){
        // TODO
        return price.compareTo(BigDecimal.ONE) >= 0;
    }
}
