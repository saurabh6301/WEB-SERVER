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

import com.books.book.entity.ScienceBooks;
import com.books.book.repository.ScienceBooksRepository;

@RestController
public class ScienceBooksController {
    @Autowired
    private ScienceBooksRepository scienceBooksRepository;

    @PostMapping("/sciencebooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ScienceBooks Savebooks(@RequestBody ScienceBooks book) {
        return scienceBooksRepository.save(book);
    }

    @GetMapping("/sciencebooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public List<ScienceBooks> getAllBooks() {
        return scienceBooksRepository.findAll();
    }

    @GetMapping("/sciencebooks/{id}")
    public ScienceBooks getAllBooks(@PathVariable int id) {
        return scienceBooksRepository.findById(id).get();
    }

    @DeleteMapping("/sciencebooks/{id}")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ResponseEntity<ScienceBooks> deleteBook(@PathVariable int id) {
        Optional<ScienceBooks> book = scienceBooksRepository.findById(id);

        if (book.isPresent()) {
            scienceBooksRepository.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/ScienceBooks/{id}")
    public ResponseEntity<ScienceBooks> updateBook(@PathVariable int id, @RequestBody ScienceBooks updatedBook) {
        Optional<ScienceBooks> existingBook = scienceBooksRepository.findById(id);

        if (existingBook.isPresent()) {
            ScienceBooks book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            ScienceBooks savedBook = scienceBooksRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
