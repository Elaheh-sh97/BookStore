package com.store.bookstore.dto;

import com.store.bookstore.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponsedto {
    private int orderId;
    private double totalPrice;
    private String status;
    private OrderItem orderItem;
    private String createdAt;

}
