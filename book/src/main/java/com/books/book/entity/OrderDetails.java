package com.books.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate unique IDs for orders
    private Long id; // Primary key for the OrderDetails entity

    private String name;
    private double price;
    private String publication;
    private String href;
    private String classname;
    private String imageUrl;
    private String details;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many orders can belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column in the order_details table
    @JsonIgnore
    private User user;

    // Default constructor
    public OrderDetails() {
    }

    // Constructor with fields
    public OrderDetails(String name, double price, String publication, String href, String classname, String imageUrl,
            String details, User user) {
        this.name = name;
        this.price = price;
        this.publication = publication;
        this.href = href;
        this.classname = classname;
        this.imageUrl = imageUrl;
        this.details = details;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
