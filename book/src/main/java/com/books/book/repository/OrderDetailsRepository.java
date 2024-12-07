package com.books.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
