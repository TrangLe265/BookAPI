package com.trangle.bookdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Jakarta Persistence API provides object relational mapping 
//JPA enitity = database table
//fields = columns 
//equivalent of JPA in pure Java is DAO (Data Access Object) and in Hibernate (another Java framework) is HQL (Hibernate Query Language)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Book {
    @Id 
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id; 

    private String isbn;    
    private String title; 
    private String author;
    private int publicationYear;
    private float price; 
    @ManyToOne
    @JsonIgnore
    private Category category; 

    public Book() {
    }

    public Book(String isbn, String title, String author, int publicationYear, float price, Category category) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
        this.category = category;
    }

    //a constructor without the category parameter
    public Book(String isbn, String title, String author, int publicationYear, float price) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString(){
        if (this.category != null){
            return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", price=" + price + ", category=" + this.getCategory() + "]";
        } else {
            return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", price=" + price + "]";
        }
    }

}
