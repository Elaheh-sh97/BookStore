package com.store.bookstore.service;

import com.store.bookstore.dto.Productdto;
import com.store.bookstore.model.Product;
import com.store.bookstore.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Productdto> getAllProducts(){

      return  productsRepository.findAll().stream()
              .map(p->new Productdto(p.getId(),p.getName(),p.getPrice(),p.getStock(),p.getDescription()))
              .collect(Collectors.toList());
    }

    public Productdto getProductById(int id){
        Product p= productsRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));;
        return new Productdto(p.getId(),p.getName(),p.getPrice(),p.getStock(),p.getDescription());
    }
}
