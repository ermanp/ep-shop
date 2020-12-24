package com.ermanp.shoppingapp.product.service;

import com.ermanp.shoppingapp.product.domain.MoneyTypes;
import com.ermanp.shoppingapp.product.domain.Product;
import com.ermanp.shoppingapp.product.domain.ProductImage;
import com.ermanp.shoppingapp.product.domain.es.ProductEs;
import com.ermanp.shoppingapp.product.model.ProductResponse;
import com.ermanp.shoppingapp.product.model.ProductSaveRequest;
import com.ermanp.shoppingapp.product.model.ProductSellerResponse;
import com.ermanp.shoppingapp.product.repository.es.ProductEsRepository;
import com.ermanp.shoppingapp.product.repository.mongo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductEsRepository productEsRepository;
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final ProductDeliveryService productDeliveryService;
    private final ProductAmountService productAmountService;
    private final ProductImageService productImageService;
    private final ProductEsService productEsService;

    public Mono<Void> deleteAll(){
        return productRepository.deleteAll();
    }

    public Flux<ProductResponse> getAll(){
        return productEsService.findAll().map(this::mapToDto);
    }

    public ProductResponse save(ProductSaveRequest request){
        Product product = Product.builder()
                .active(Boolean.TRUE).
                        code("PR0001")
                .categoryId(request.getCategoeyId())
                .companyId(request.getSellerId())
                .description(request.getDescription())
                .id(request.getId())
                .features(request.getFeatures())
                .name(request.getName())
                .images(request.getImages().stream().map(i -> new ProductImage(ProductImage.ImageType.FEATURE,i)).collect(Collectors.toList()))
                .build();
        product = productRepository.save(product).block();
        productEsService.saveNewProduct(product);
        //rredisi guncelle

        return this.mapToDto(productEsService.saveNewProduct(product).block());

    }

    private ProductResponse mapToDto(ProductEs item) {
        if(item == null){
            return null;
        }
        BigDecimal productPrice = productPriceService.getByMoneyType(item.getId(), MoneyTypes.USD);
        return ProductResponse.builder()
                .price(productPrice)
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescription())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.checkFreeDelivery(item.getId(), productPrice))
                .moneyType(MoneyTypes.EURO)
                .image(productImageService.getProductMainImage(item.getId()))
                .seller(ProductSellerResponse.builder().id(item.getSeller().getName()).build())
                .build();
    }

    public Mono<Long> count() {
        return productRepository.count();
    }
}
