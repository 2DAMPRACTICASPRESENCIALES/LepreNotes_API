package com.lepreteam.leprenotes.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Order;
import com.lepreteam.leprenotes.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(long user_id) {
        return orderRepository.findOrdersByUserId(user_id);
    }

    @Override
    public Order addOrders(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long order_id) throws NotFoundException {
        Order order = orderRepository.findById(order_id)
                    .orElseThrow(() -> new NotFoundException(new Order()));
        orderRepository.delete(order);
    }
    
}
