package com.store.bookstore.service;

import com.store.bookstore.dto.Productdto;
import com.store.bookstore.model.Product;
import com.store.bookstore.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;
    private RedisTemplate<String, Object> redisTemplate;


    public ProductsService(ProductsRepository productsRepository, RedisTemplate<String, Object> redisTemplate) {
        this.productsRepository = productsRepository;
        this.redisTemplate = redisTemplate;

    }

    public List<Productdto> getAllProducts(){
        Boolean productExist=redisTemplate.opsForHash().hasKey("products","allProducts");
        List<Productdto> products=new ArrayList<>();
        if(productExist){
            products= (List<Productdto>)redisTemplate.opsForHash().get("products","allProducts");
        }else{
            products = productsRepository.findAll().stream()
                    .map(p->new Productdto(p.getId(),p.getName(),p.getPrice(),p.getStock(),p.getDescription()))
                    .collect(Collectors.toList());
            redisTemplate.opsForHash().put("products","allProducts",products);
        }

      return products;
    }

    public Productdto getProductById(int id){
        Boolean productExist=redisTemplate.opsForHash().hasKey("products","allProducts");
        Productdto product;
        List<Productdto> productList=new ArrayList<>();
        if(productExist){
            productList=(List<Productdto>) redisTemplate.opsForHash().get("products","allProducts");
            product=productList.stream().filter(p->p.getId()==id).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
        }else{
            Product p= productsRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
           product=new Productdto(p.getId(),p.getName(),p.getPrice(),p.getStock(),p.getDescription());

        }

        return product;
    }
}
