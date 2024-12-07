package com.books.book.repository;

import com.books.book.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query methods can be added here if needed

    // Example: Find a user by their email
    User findByEmail(String email);

    User findByUserId(long userId);

    User findByUsername(String username);

    List<User> findAllByOrderByCreatedAtDesc(); // Fetch users sorted by creation time in descending order
}
