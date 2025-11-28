package com.store.bookstore.service;

import com.store.bookstore.dto.Productdto;
import com.store.bookstore.model.Product;
import com.store.bookstore.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    @Mock
    ProductsRepository productsRepository;
    @InjectMocks
    ProductsService productsService;


@Test
    void getAllproductsTest(){
    Product product1=new Product("bookA",200000,"A fantasy book",1);
    Product product2=new Product("bookB",400000,"A fantasy book",2);
    List<Product> products=List.of(product1,product2);
    when(productsRepository.findAll()).thenReturn(products);

    List<Productdto> testList=productsService.getAllProducts();

    assertEquals(2,testList.size());
     Productdto pro1=testList.get(0);
     assertEquals("bookA",pro1.getName());
     assertEquals(200000,pro1.getPrice());
     assertEquals("A fantasy book",pro1.getDescription());
     assertEquals(1,pro1.getStock());




}




}