package com.ermanp.shoppingapp.product.service;

import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
    public String getProductMainImage(String productId){
        return "https://productimages.hepsiburada.net/s/25/550/10126707621938.jpg/format:webp";
    }
}
