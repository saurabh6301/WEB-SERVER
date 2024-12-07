package com.books.book.repository; // "repository" instead of "reposaretry"

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.entity.GeographyBooks;

public interface GeographyBooksRepository extends JpaRepository<GeographyBooks, Integer> {

}
