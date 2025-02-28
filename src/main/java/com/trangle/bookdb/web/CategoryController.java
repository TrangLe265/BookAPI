package com.trangle.bookdb.web;

import org.springframework.web.bind.annotation.RestController;

import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;        

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }; 

    @GetMapping("/categories")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
   

}
