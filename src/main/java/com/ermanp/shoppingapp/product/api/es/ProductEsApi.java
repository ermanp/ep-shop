package com.ermanp.shoppingapp.product.api.es;

import com.ermanp.shoppingapp.product.domain.es.ProductEs;
import com.ermanp.shoppingapp.product.service.ProductEsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/es/products")
@RequiredArgsConstructor
public class ProductEsApi {

    private final ProductEsService service;

    @GetMapping("/all")
    public Flux<ProductEs> findAllProducts(){
        return service.findAll();
    }
}
