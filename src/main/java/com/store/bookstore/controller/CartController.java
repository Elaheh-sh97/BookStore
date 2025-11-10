package com.store.bookstore.controller;

import com.store.bookstore.dto.AddToCartdto;
import com.store.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class CartController {
    @Autowired
private CartService cartService;
//    @GetMapping("/cart/{id}")
//public ResponseEntity<Cart> getCartById(@PathVariable int id) {
//
//}

public ResponseEntity<String> addToCart(AddToCartdto addToCartdto){
    cartService.addToCart(addToCartdto);
}




}
