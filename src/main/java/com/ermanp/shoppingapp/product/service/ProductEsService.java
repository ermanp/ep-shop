package com.ermanp.shoppingapp.product.service;

import com.ermanp.shoppingapp.product.domain.Product;
import com.ermanp.shoppingapp.product.domain.es.CategoryEs;
import com.ermanp.shoppingapp.product.domain.es.CompanyEs;
import com.ermanp.shoppingapp.product.domain.es.ProductEs;
import com.ermanp.shoppingapp.product.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductEsService {

    private final ProductEsRepository productEsRepository;

    public Mono<ProductEs> saveNewProduct(Product product){
        return productEsRepository.save(
        ProductEs.builder()
                .active(product.getActive())
                .code(product.getCode())
                .description(product.getDescription())
                .features(product.getFeatures())
                .id(product.getId())
                .name(product.getName())
                //TODO get company name and code
                .seller(CompanyEs.builder().id(product.getCompanyId()).name("").build())
                // TODO get company name and code
                .category(CategoryEs.builder().id(product.getCategoryId()).name("Test").build())
                .build());
    }

    public Flux<ProductEs> findAll() {
        return productEsRepository.findAll();
    }
}
