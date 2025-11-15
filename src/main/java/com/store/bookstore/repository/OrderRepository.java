package com.store.bookstore.repository;


import com.store.bookstore.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
}
