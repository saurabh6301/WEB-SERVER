package com.books.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.books.book.entity.OrderDetails;
import com.books.book.repository.OrderDetailsRepository;

@RestController
@CrossOrigin(origins = "https://bookstore-growpath.vercel.app")

public class OrderDetailsController {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @PostMapping("/orderdetails")
    public ResponseEntity<OrderDetails> saveOrderDetails(@RequestBody OrderDetails books) {
        try {
            // Log the request body
            System.out.println("Received OrderDetails: " + books);

            OrderDetails savedOrder = orderDetailsRepository.save(books);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/orderdetails")
    public List<OrderDetails> getAllBooks() {
        return orderDetailsRepository.findAll();
    }

    @GetMapping("/orderdetails/{id}")
    public OrderDetails getAllBooks(@PathVariable int id) {
        return orderDetailsRepository.findById(id).get();
    }

    @DeleteMapping("/orderdetails/{id}")
    public ResponseEntity<OrderDetails> deleteBook(@PathVariable int id) {
        Optional<OrderDetails> book = orderDetailsRepository.findById(id);

        if (book.isPresent()) {
            orderDetailsRepository.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/OrderDetails/{id}")
    public ResponseEntity<OrderDetails> updateBook(@PathVariable int id, @RequestBody OrderDetails updatedBook) {
        Optional<OrderDetails> existingBook = orderDetailsRepository.findById(id);

        if (existingBook.isPresent()) {
            OrderDetails book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            OrderDetails savedBook = orderDetailsRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
