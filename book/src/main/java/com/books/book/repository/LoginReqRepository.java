package com.books.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.book.entity.LoginReq;

public interface LoginReqRepository extends JpaRepository<LoginReq, String> {

}
