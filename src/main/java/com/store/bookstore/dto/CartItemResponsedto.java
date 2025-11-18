package com.store.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemResponsedto {
    private int id;
    private int productId;
    private String name;
    private double price;
    private int quantity;
    private double totalPrice;
}
