package com.books.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.books.book.entity.OrderDetails;
import com.books.book.entity.User;
import com.books.book.repository.OrderDetailsRepository;
import com.books.book.repository.UserRepository;

@RestController
public class UserOrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // Endpoint to fetch all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Endpoint to fetch a user by username
    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    // Endpoint to create a new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Endpoint to fetch all orders
    @GetMapping("/orders")
    public List<OrderDetails> getAllOrders() {
        return orderDetailsRepository.findAll();
    }

    // Endpoint to fetch orders by username
    @GetMapping("/users/{username}/orders")
    public List<OrderDetails> getOrdersByUsername(@PathVariable String username) {
        return orderDetailsRepository.findAll().stream()
                .filter(order -> order.getUser().getUsername().equals(username))
                .toList();
    }

}
