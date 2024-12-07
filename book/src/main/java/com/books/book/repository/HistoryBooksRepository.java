package com.books.book.repository; // "repository" instead of "reposaretry"

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.entity.HistoryBooks;

public interface HistoryBooksRepository extends JpaRepository<HistoryBooks, Integer> {

}
