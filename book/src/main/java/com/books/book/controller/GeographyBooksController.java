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

import com.books.book.entity.GeographyBooks;
import com.books.book.repository.GeographyBooksRepository;

@RestController
public class GeographyBooksController {
    @Autowired
    private GeographyBooksRepository geographyBooksRepository;

    @PostMapping("/geographybooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public GeographyBooks Savebooks(@RequestBody GeographyBooks book) {
        return geographyBooksRepository.save(book);
    }

    @GetMapping("/geographybooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public List<GeographyBooks> getAllBooks() {
        return geographyBooksRepository.findAll();
    }

    @GetMapping("/geographybooks/{id}")
    public GeographyBooks getAllBooks(@PathVariable int id) {
        return geographyBooksRepository.findById(id).get();
    }

    @DeleteMapping("/geographybooks/{id}")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ResponseEntity<GeographyBooks> deleteBook(@PathVariable int id) {
        Optional<GeographyBooks> book = geographyBooksRepository.findById(id);

        if (book.isPresent()) {
            geographyBooksRepository.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/geographybooks/{id}")
    public ResponseEntity<GeographyBooks> updateBook(@PathVariable int id, @RequestBody GeographyBooks updatedBook) {
        Optional<GeographyBooks> existingBook = geographyBooksRepository.findById(id);

        if (existingBook.isPresent()) {
            GeographyBooks book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            GeographyBooks savedBook = geographyBooksRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
