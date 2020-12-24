package com.ermanp.shoppingapp.product.api.v1;

import com.ermanp.shoppingapp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/modify/products")
@RequiredArgsConstructor
public class ProductModificationApi {

    private final ProductService productService;

    @DeleteMapping("/delete/all")
    public Mono<Void> deleteAll(){
        return productService.deleteAll();
    }
}
