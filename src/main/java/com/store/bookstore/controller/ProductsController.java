package com.store.bookstore.controller;

import com.store.bookstore.dto.Productsdto;
import com.store.bookstore.model.Products;
import com.store.bookstore.repository.ProductsRepository;
import com.store.bookstore.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @GetMapping("/books")
    public ResponseEntity<List<Productsdto>> getAllproducts(){
        List<Productsdto> products = productsService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Productsdto> getProductById(@PathVariable int id){
     return ResponseEntity.ok().body(productsService.getProductById(id));
    }
}
