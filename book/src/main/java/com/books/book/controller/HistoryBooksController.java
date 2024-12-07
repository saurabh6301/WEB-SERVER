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

import com.books.book.entity.HistoryBooks;
import com.books.book.repository.HistoryBooksRepository;

@RestController
public class HistoryBooksController {
    @Autowired
    private HistoryBooksRepository historyBooksRepository;

    @PostMapping("/historybooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public HistoryBooks Savebooks(@RequestBody HistoryBooks book) {
        return historyBooksRepository.save(book);
    }

    @GetMapping("/historybooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public List<HistoryBooks> getAllBooks() {
        return historyBooksRepository.findAll();
    }

    @GetMapping("/historybooks/{id}")
    public HistoryBooks getAllBooks(@PathVariable int id) {
        return historyBooksRepository.findById(id).get();
    }

    @DeleteMapping("/historybooks/{id}")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ResponseEntity<HistoryBooks> deleteBook(@PathVariable int id) {
        Optional<HistoryBooks> book = historyBooksRepository.findById(id);

        if (book.isPresent()) {
            historyBooksRepository.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/HistoryBooks/{id}")
    public ResponseEntity<HistoryBooks> updateBook(@PathVariable int id, @RequestBody HistoryBooks updatedBook) {
        Optional<HistoryBooks> existingBook = historyBooksRepository.findById(id);

        if (existingBook.isPresent()) {
            HistoryBooks book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            HistoryBooks savedBook = historyBooksRepository.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
