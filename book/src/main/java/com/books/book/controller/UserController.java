package com.books.book.controller;

import com.books.book.entity.User;
import com.books.book.entity.LoginReq;
import com.books.book.entity.OrderDetails;
import com.books.book.repository.UserRepository;
import com.books.book.repository.LoginReqRepository;
import com.books.book.repository.OrderDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://bookstore-growpath.vercel.app", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private LoginReqRepository loginReqRepository;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if a user with the same username or email already exists
        if (userRepository.findByUsername(user.getUsername()) != null
                || userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username and Email already exists  after confrom the order you can order more books.");
        }

        // Save the new user
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }
    // login user

    // Get all users
    @GetMapping("/register")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        // Retrieve all users ordered by creation time (descending)
        List<User> users = new ArrayList<>(userRepository.findAllByOrderByCreatedAtDesc());
        return ResponseEntity.ok(users);
    }

    // Retrieve a user by username
    @GetMapping("/login")
    public ResponseEntity<?> getUserDetails(@RequestBody LoginReq loginReq) {
        // Validate the incoming request body
        if (loginReq == null || loginReq.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

        // Retrieve the logged-in username from the request
        String loggedInUsername = loginReq.getUsername();

        // Find user by username
        User user = userRepository.findByUsername(loggedInUsername);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Mask sensitive information
        user.setPassword(null);

        // Return user details

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginReq loginReq) {

        // Validate the username and password
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();

        if (username == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password cannot be null");
        }

        // Authenticate the user
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Assuming plain text passwords (in practice, you should use hashed passwords)
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        user.setPassword(null); // Mask sensitive information

        // Return a success message
        return ResponseEntity.status(HttpStatus.OK).body("User login successfully!");

    }

    @PutMapping("/register/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User existingUser = userRepository.findByUserId(userId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        // Update fields
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        userRepository.save(existingUser);

        return ResponseEntity.ok("User update successfully.");
    }

    // Delete user
    @DeleteMapping("/register/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // Retrieve a user's orders by userId
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderDetails>> getUserOrders(@PathVariable Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(user.getOrderDetails());
    }

    // Add an order for a user
    @PostMapping("/{userId}/orders")
    public ResponseEntity<String> addOrder(@PathVariable Long userId, @RequestBody OrderDetails orderDetails) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Associate the order with the user and save
        orderDetails.setUser(user);
        orderDetailsRepository.save(orderDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order added successfully.");
    }
}
