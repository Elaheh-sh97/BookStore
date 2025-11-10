package com.store.bookstore.controller;

import com.store.bookstore.dto.AddToCartdto;
import com.store.bookstore.dto.AuthResponse;
import com.store.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore/cart")
public class CartController {
    @Autowired
private CartService cartService;

@PostMapping("/item")
public ResponseEntity<String> addToCart(@RequestBody AddToCartdto addToCartdto){
    String body=  cartService.addToCart(addToCartdto);
    return ResponseEntity.ok().body(body);
}




}
