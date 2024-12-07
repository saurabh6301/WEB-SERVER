package com.books.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoginReq {
    @Id
    private String username;
    private String password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReq() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
