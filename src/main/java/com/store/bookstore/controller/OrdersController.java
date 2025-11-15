package com.store.bookstore.controller;


import com.store.bookstore.dto.OrderRequestdto;
import com.store.bookstore.repository.OrderItemRepository;
import com.store.bookstore.repository.OrderRepository;
import com.store.bookstore.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/orders")
    public String createOrderFromCart(@RequestBody OrderRequestdto orderRequestdto) {
      ordersService.createOrderFromCart(orderRequestdto);
      return "success";
    }
}
