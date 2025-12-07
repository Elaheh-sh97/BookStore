package com.store.bookstore.controller;

import com.store.bookstore.dto.Productdto;
import com.store.bookstore.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class ProductsController {

    private ProductsService productsService;


    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;

    }

    @GetMapping("/books")
    public ResponseEntity<List<Productdto>> getAllproducts(){
        List<Productdto> products= productsService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Productdto> getProductById(@PathVariable int id){
     return ResponseEntity.ok().body(productsService.getProductById(id));
    }
}
