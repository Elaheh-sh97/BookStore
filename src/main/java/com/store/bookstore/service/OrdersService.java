package com.store.bookstore.service;

import com.store.bookstore.dto.AddToCartdto;
import com.store.bookstore.dto.OrderRequestdto;
import com.store.bookstore.model.*;
import com.store.bookstore.repository.OrderItemRepository;
import com.store.bookstore.repository.OrderRepository;
import com.store.bookstore.repository.ProductsRepository;
import com.store.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private OrderRepository orderRepository;
    public String createOrderFromCart(OrderRequestdto orderRequestdto) {
        Users user = userRepository.findById(orderRequestdto.getUsers_id()).orElseThrow(() -> new RuntimeException("user not found"));
        List<AddToCartdto> items = orderRequestdto.getItems();
        List<OrderItem> orderItemList = new ArrayList<>();
        Orders newOrder = new Orders();
        double allTotalPrice = 0;
        for (AddToCartdto item : items) {
            OrderItem orderItem = new OrderItem();
            Product product = productsRepository.findById(item.getProduct_id()).orElseThrow(() -> new RuntimeException("product not found"));
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(product.getPrice() * item.getQuantity());
            orderItem.setOrders(newOrder);
            orderItemList.add(orderItem);
            allTotalPrice += orderItem.getTotalPrice();
        }

        newOrder.setUsers(user);
        newOrder.setOrderItemList(orderItemList);
        newOrder.setTotalPrice(allTotalPrice);
        newOrder.setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(newOrder);
        return "order created successfully";
    }
}
