package com.books.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GeographyBooks {
    @Id
    private int Id;
    private String name;
    private int price;
    private String publication;
    private String href;
    private String classname;
    private String imageUrl;
    private String details;

    public GeographyBooks(int id, String name, int price, String publication, String href, String classname,
            String imageUrl, String details) {
        Id = id;
        this.name = name;
        this.price = price;
        this.publication = publication;
        this.href = href;
        this.classname = classname;
        this.imageUrl = imageUrl;
        this.details = details;
    }

    public GeographyBooks() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;

    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
