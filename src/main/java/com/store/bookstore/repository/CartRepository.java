package com.store.bookstore.repository;

import com.store.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Boolean existsByUserId(Integer id);

    Optional<Cart> findByUserId(int id);
}
