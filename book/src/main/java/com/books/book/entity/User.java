package com.books.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate unique IDs for users
    private long userId; // Primary key for the User entity

    @Column(unique = true, nullable = false)
    private String username; // Unique constraint for username

    @Column(unique = true, nullable = false)
    private String email; // Unique constraint for email

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    @Size(max = 255, message = "Address cannot exceed 255 characters") // Limit address length
    private String address;

    @Column(nullable = true)
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") // Validate phone number format
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Configures one-to-many
                                                                                   // relationship
    private List<OrderDetails> orderDetails; // List of orders associated with this user

    // Default constructor
    public User() {
    }

    // Constructor with fields
    public User(String username, String email, String password, String address, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
        // Ensure bidirectional consistency
        if (orderDetails != null) {
            orderDetails.forEach(order -> order.setUser(this));
        }
    }
}
