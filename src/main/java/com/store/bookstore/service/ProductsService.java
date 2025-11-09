package com.store.bookstore.service;

import com.store.bookstore.dto.Productsdto;
import com.store.bookstore.model.Products;
import com.store.bookstore.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Productsdto> getAllProducts(){
      return  productsRepository.findAll().stream()
              .map(p->new Productsdto(p.getId(),p.getName(),p.getPrice(),p.getStock(),p.getDescription()))
              .collect(Collectors.toList());
    }

    public Productsdto getProductById(int id){
        Products p= productsRepository.findById(id);
        return new Productsdto(p.getId(),p.getName(),p.getPrice(),p.getStock(),p.getDescription());
    }
}
