package com.books.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.books.book.entity.MathBooks;
import com.books.book.repository.MathBooksRepository;

@RestController
public class MathBooksController {
    @Autowired
    private MathBooksRepository mathBooksRepository;

    @PostMapping("/mathbooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public MathBooks Savebooks(@RequestBody MathBooks book) {
        return mathBooksRepository.save(book);
    }

    @GetMapping("/mathbooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public List<MathBooks> getAllBooks() {
        return mathBooksRepository.findAll();
    }

    @GetMapping("/mathbooks/{id}")
    public MathBooks getAllBooks(@PathVariable int id) {
        return mathBooksRepository.findById(id).get();
    }

    @DeleteMapping("/mathbooks/{id}")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ResponseEntity<MathBooks> deleteBook(@PathVariable int id) {
        Optional<MathBooks> book = mathBooksRepository.findById(id);

        if (book.isPresent()) {
            mathBooksRepository.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/MathBooks/{id}")
    public ResponseEntity<MathBooks> updateBook(@PathVariable int id, @RequestBody MathBooks updatedBook) {
        Optional<MathBooks> existingBook = mathBooksRepository.findById(id);

        if (existingBook.isPresent()) {
            MathBooks book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            MathBooks savedBook = mathBooksRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
