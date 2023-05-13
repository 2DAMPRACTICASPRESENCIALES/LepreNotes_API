package com.lepreteam.leprenotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.ErrorMessage;
import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Order;
import com.lepreteam.leprenotes.service.OrderService;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        logger.info("Begin get orders");
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/{user_id}/users")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable long user_id) throws NotFoundException {
        logger.info("Begin get order by user id");
        return new ResponseEntity<>(orderService.getOrdersByUserId(user_id), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws NotFoundException {
        logger.info("Begin post orders");
        Order newOrder = orderService.addOrders(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) throws NotFoundException {
        logger.info("Begin delete orders by Id");
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException nfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
