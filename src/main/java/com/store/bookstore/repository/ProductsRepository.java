package com.store.bookstore.repository;

import com.store.bookstore.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
    List<Products> findAll();
    Products findById(int id);
}
