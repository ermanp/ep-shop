package com.ermanp.shoppingapp.product.domain.mock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MockData {

    private String name;
    private String surname;
    private String email;
    private String address;
}
