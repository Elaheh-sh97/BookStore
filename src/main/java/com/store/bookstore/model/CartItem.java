package com.store.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private int quantity;
    private double price;
    private double totalPrice;
    public CartItem(Product product, Cart cart, int quantity, double price, double totalPrice) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
