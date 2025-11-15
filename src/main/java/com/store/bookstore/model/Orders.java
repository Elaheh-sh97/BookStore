package com.store.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private double totalPrice;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;


}
