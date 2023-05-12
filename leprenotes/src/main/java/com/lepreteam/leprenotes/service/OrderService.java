package com.lepreteam.leprenotes.service;

import java.util.List;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Order;

public interface OrderService {
    List<Order> findAll();
    List<Order> getOrdersByUserId(long user_id);
    Order addOrders(Order order);
    void deleteOrder(long order_id) throws NotFoundException;
}
