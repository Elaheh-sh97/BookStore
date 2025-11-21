package com.store.bookstore.service;

import com.store.bookstore.dto.AddToCartResponsedto;
import com.store.bookstore.dto.AddToCartdto;
import com.store.bookstore.dto.CartItemResponsedto;
import com.store.bookstore.dto.UpdateCartItemdto;
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
import java.util.List;

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

    public AddToCartResponsedto addToCart(AddToCartdto addToCartdto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        Product product = productsRepository.findById(addToCartdto.getProduct_id()).orElseThrow(() -> new RuntimeException("Product not found"));
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(new Cart(user.getId(), "Active")));
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> new CartItem(product, cart, 0, 0, 0));
        int productQuantity = addToCartdto.getQuantity();
        double totalPrice = product.getPrice() * productQuantity;
        cartItem.setQuantity(productQuantity);
        cartItem.setPrice(product.getPrice());
        cartItem.setTotalPrice(totalPrice);
        cartItemRepository.save(cartItem);
        List<CartItem> cartItemsList = cart.getCartItems();
        cart.setCartTotalPrice(0);
        cart.setCartTotalQuantity(0);
        for (CartItem cartItem1 : cartItemsList) {
            cart.setCartTotalPrice(cart.getCartTotalPrice() + cartItem1.getTotalPrice());
            cart.setCartTotalQuantity(cart.getCartTotalQuantity() + cartItem1.getQuantity());
        }
        cartRepository.save(cart);
        CartItemResponsedto cartItemResponsedto = new CartItemResponsedto(cartItem.getId(), cartItem.getProduct().getId(), cartItem.getProduct().getName(), cartItem.getPrice(), cartItem.getQuantity(), cartItem.getTotalPrice());
        AddToCartResponsedto addToCartResponsedto = new AddToCartResponsedto("Product added to Cart successfully", cartItemResponsedto, cart.getCartTotalPrice(), cart.getCartTotalQuantity());

        return addToCartResponsedto;


    }

    public AddToCartResponsedto deleteCartItem(int id) {
        AddToCartResponsedto addToCartResponsedto;
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        int cartId = cartItem.getCart().getId();
        cartItemRepository.deleteById(id);
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        List<CartItem> cartItemList = cartItemRepository.findByCartId(cartId);
        if(cartItemList.isEmpty()){
            cartRepository.deleteById(cartId);
             addToCartResponsedto = new AddToCartResponsedto("cart item removed sucessfully", null, 0, 0);

        }else{
            cart.setCartTotalPrice(0);
            cart.setCartTotalQuantity(0);
            for (CartItem cartItem1 : cartItemList) {
                cart.setCartTotalPrice(cart.getCartTotalPrice() + cartItem1.getTotalPrice());
                cart.setCartTotalQuantity(cart.getCartTotalQuantity() + cartItem1.getQuantity());
            }
            cartRepository.save(cart);
             addToCartResponsedto = new AddToCartResponsedto("cart item removed sucessfully", null, cart.getCartTotalPrice(), cart.getCartTotalQuantity());

        }


        return addToCartResponsedto;
    }

    public AddToCartResponsedto updateCartItem(UpdateCartItemdto updateCartItemdto) {
        CartItem cartitem=cartItemRepository.findById(updateCartItemdto.getCartItemId()).orElseThrow(()->new RuntimeException("Cart item not found"));
        cartitem.setQuantity(updateCartItemdto.getQuantity());
        cartitem.setTotalPrice(cartitem.getQuantity()*cartitem.getProduct().getPrice());
        cartItemRepository.save(cartitem);
        Cart cart=cartitem.getCart();
        List<CartItem> cartItemList=cart.getCartItems();
        cart.setCartTotalQuantity(0);
        cart.setCartTotalPrice(0);
        int totalQuntity=0;
        int totalPrice=0;
        for(CartItem cartItem1:cartItemList){
            totalQuntity += cartItem1.getQuantity();
            totalPrice+=cartItem1.getTotalPrice();
        }
        cart.setCartTotalQuantity(totalQuntity);
        cart.setCartTotalPrice(totalPrice);
        cartRepository.save(cart);
        AddToCartResponsedto addToCartResponsedto=new AddToCartResponsedto("CartItem Updated Successfully",null,cart.getCartTotalPrice(), cart.getCartTotalQuantity());
    return addToCartResponsedto;
    }
}
