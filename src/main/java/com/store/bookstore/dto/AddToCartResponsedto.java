package com.store.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddToCartResponsedto {
    private String message;
    private CartItemResponsedto CartItemResponsedto;
    private double totalPrice;
    private int totalQuantity;
}
