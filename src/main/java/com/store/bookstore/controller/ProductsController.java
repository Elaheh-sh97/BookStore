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

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Object> hashOperations;
    public ProductsController(ProductsService productsService, RedisTemplate<String, Object> redisTemplate, HashOperations<String, String, Object> hashOperations) {
        this.productsService = productsService;
        this.redisTemplate = redisTemplate;
        this.hashOperations = hashOperations;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Productdto>> getAllproducts(){
        Object chechedProduct=redisTemplate.opsForHash().hasKey("products","allProducts");
        List<Productdto> products;
        if(chechedProduct!=null){
     products= (List<Productdto>)redisTemplate.opsForHash().get("products","allProducts");
        }else{
     products = productsService.getAllProducts();
     redisTemplate.opsForHash().put("products","allProducts",products);

        }
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Productdto> getProductById(@PathVariable int id){
     return ResponseEntity.ok().body(productsService.getProductById(id));
    }
}
