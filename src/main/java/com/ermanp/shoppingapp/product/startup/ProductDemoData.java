package com.ermanp.shoppingapp.product.startup;

import com.ermanp.shoppingapp.product.domain.MoneyTypes;
import com.ermanp.shoppingapp.product.model.category.CategoryResponse;
import com.ermanp.shoppingapp.product.model.category.CategorySaveRequest;
import com.ermanp.shoppingapp.product.model.product.ProductSaveRequest;
import com.ermanp.shoppingapp.product.service.ProductService;
import com.ermanp.shoppingapp.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Component // TODO difference between component and service
@RequiredArgsConstructor
public class ProductDemoData {
    private final ProductService productService;
    private final CategoryService categoryService;

    @EventListener(ApplicationReadyEvent.class)
    public void migrate(){
        Long countOfData = productService.count().block();
        if(countOfData.equals(0L)) {
            CategoryResponse elektronik = categoryService.save(CategorySaveRequest.builder().name("Elektronik").build());
            CategoryResponse telefon = categoryService.save(CategorySaveRequest.builder().name("Cep Telefonu").build());


            IntStream.range(0, 20).forEach(item -> {
                productService.save(
                        ProductSaveRequest.builder()
                                .sellerId(UUID.randomUUID().toString())
                                .features("<li>Black Color</li> <li>Aluminum Case</li> <li>2 Years Warranty</li> <li>5 Inch (35x55mm)</li>")
                                .id(UUID.randomUUID().toString())
                                .description("Product Description " + item)
                                .money(MoneyTypes.USD)
                                .categoeyId(UUID.randomUUID().toString())
                                .name("Product Name " + item)
                                .price(BigDecimal.TEN)
                                .images(List.of("https://productimages.hepsiburada.net/s/25/550/10126707621938.jpg"))
                                .build());

            });
        }
    }
}
