package com.ermanp.shoppingapp.product.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MoneyTypes {
    USD("Dolar","$"),
    EURO("Euro","E"),
    TL("Lira","TL");

    private String label; // Dollar
    private String symbol; // $


}
