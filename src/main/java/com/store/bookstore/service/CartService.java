package com.store.bookstore.service;

import com.store.bookstore.dto.AddToCartdto;
import com.store.bookstore.model.Cart;
import com.store.bookstore.model.CartItem;
import com.store.bookstore.model.Product;
import com.store.bookstore.model.Users;
import com.store.bookstore.repository.CartItemRepository;
import com.store.bookstore.repository.CartRepository;
import com.store.bookstore.repository.ProductsRepository;
import com.store.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public String addToCart(AddToCartdto addToCartdto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        Product product = productsRepository.findById(addToCartdto.getProduct_id()).orElseThrow(() -> new RuntimeException("Product not found"));
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(new Cart(user.getId(), "Active")));
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> new CartItem(product, cart, 0, 0));
        int productQuantity = addToCartdto.getQuantity();
        double totalPrice = product.getPrice() * productQuantity;
        cartItem.setQuantity(productQuantity);
        cartItem.setPrice(totalPrice);

        cartItemRepository.save(cartItem);
        return "Success";

    }
}
