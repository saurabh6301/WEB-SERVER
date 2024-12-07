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

import com.books.book.entity.HindiBooks;
import com.books.book.repository.HindiBooksRepository;

@RestController
public class HindiBooksController {
    @Autowired
    private HindiBooksRepository hindiBooksReposatery;

    @PostMapping("/hindibooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public HindiBooks Savebooks(@RequestBody HindiBooks book) {
        return hindiBooksReposatery.save(book);
    }

    @GetMapping("/hindibooks")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public List<HindiBooks> getAllBooks() {
        return hindiBooksReposatery.findAll();
    }

    @GetMapping("/hindibooks/{id}")
    public HindiBooks getAllBooks(@PathVariable int id) {
        return hindiBooksReposatery.findById(id).get();
    }

    @DeleteMapping("/hindibooks/{id}")
    @CrossOrigin(origins = "https://bookstore-growpath.vercel.app")
    public ResponseEntity<HindiBooks> deleteBook(@PathVariable int id) {
        Optional<HindiBooks> book = hindiBooksReposatery.findById(id);

        if (book.isPresent()) {
            hindiBooksReposatery.deleteById(id);
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/hindibooks/{id}")
    public ResponseEntity<HindiBooks> updateBook(@PathVariable int id, @RequestBody HindiBooks updatedBook) {
        Optional<HindiBooks> existingBook = hindiBooksReposatery.findById(id);

        if (existingBook.isPresent()) {
            HindiBooks book = existingBook.get();
            book.setId(updatedBook.getId());
            book.setClassname(updatedBook.getClassname());
            book.setPublication(updatedBook.getPublication());
            book.setHref(updatedBook.getHref());
            book.setPrice(updatedBook.getPrice());
            book.setDetails(updatedBook.getDetails());
            book.setImageUrl(updatedBook.getImageUrl());

            HindiBooks savedBook = hindiBooksReposatery.save(book);
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
