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

import com.books.book.entity.EnglishBooks;
import com.books.book.repository.EnglishBooksRepository;

@RestController
public class EnglishBooksController {
    @Autowired
    private EnglishBooksRepository englishBooksRepository;

    @PostMapping("/englishbooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public EnglishBooks Savebooks(@RequestBody EnglishBooks book) {
        return englishBooksRepository.save(book);
    }

    @GetMapping("/englishbooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public List<EnglishBooks> getAllBooks() {
        return englishBooksRepository.findAll();
    }

    @GetMapping("/englishbooks/{id}")
    public EnglishBooks getAllBooks(@PathVariable int id) {
        return englishBooksRepository.findById(id).get();
    }

    @DeleteMapping("/englishbooks/{id}")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ResponseEntity<EnglishBooks> deleteBook(@PathVariable int id) {
        Optional<EnglishBooks> book = englishBooksRepository.findById(id);

        if (book.isPresent()) {
            englishBooksRepository.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/englishbooks/{id}")
    public ResponseEntity<EnglishBooks> updateBook(@PathVariable int id, @RequestBody EnglishBooks updatedBook) {
        Optional<EnglishBooks> existingBook = englishBooksRepository.findById(id);

        if (existingBook.isPresent()) {
            EnglishBooks book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            EnglishBooks savedBook = englishBooksRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
