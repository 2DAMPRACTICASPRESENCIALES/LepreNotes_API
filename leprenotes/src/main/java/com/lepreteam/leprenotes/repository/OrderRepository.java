package com.lepreteam.leprenotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lepreteam.leprenotes.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    
    @Query( value = "SELECT * FROM orders WHERE user_id=?", nativeQuery = true)
    List<Order> findOrdersByUserId(long user_id);
}
