package com.trangle.bookdb.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;

    //The CascadeType.ALL option means that all operations on the Category entity (parent) will be cascaded to the Book (child) entity.
    //The mappedBy attribute is used to specify the entity property that owns the relationship.
    //In this case, the Book entity owns the relationship.
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "category")
    private List<Book> books;

    public Category() {
    }

    public Category(String categoryName) {
        super();
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
	public String toString() {
		return "Category [catId=" + categoryId + ", catName=" + categoryName + "]";
	}

 
}
