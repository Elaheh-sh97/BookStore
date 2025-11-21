package com.store.bookstore.controller;

import com.store.bookstore.dto.AddToCartResponsedto;
import com.store.bookstore.dto.AddToCartdto;
import com.store.bookstore.dto.UpdateCartItemdto;
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
public ResponseEntity<AddToCartResponsedto> addToCart(@RequestBody AddToCartdto addToCartdto){
    AddToCartResponsedto reponse=  cartService.addToCart(addToCartdto);
    return ResponseEntity.ok().body(reponse);
}

@PostMapping("/item/{id}")
public ResponseEntity<AddToCartResponsedto> deleteCartItem(@PathVariable int id){
 AddToCartResponsedto response= cartService.deleteCartItem(id);
 return ResponseEntity.ok().body(response);
}

@PutMapping("/item/update")
public ResponseEntity<AddToCartResponsedto> updateCartItem(@RequestBody UpdateCartItemdto updateCartItemdto){
    AddToCartResponsedto response=cartService.updateCartItem(updateCartItemdto);
    return ResponseEntity.ok().body(response);
}
}
