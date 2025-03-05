package com.trangle.bookdb.web;

import org.springframework.web.bind.annotation.RestController;

import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.CategoryRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api") 
public class CategoryController {

    @Autowired
    private final CategoryRepository categoryRepository;        

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }; 

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("/categories")
    public @ResponseBody Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category) {
        Category savedCategory = categoryRepository.save(category); 
        
        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/categories/{categoryId}")
    public @ResponseBody ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId){
        
        Optional<Category> categoryToDelete = categoryRepository.findById(categoryId);

        if (!categoryToDelete.isPresent()){
            throw new ResourceNotFoundException("No category found with it: " + categoryId); 
        }; 

        categoryRepository.delete(categoryToDelete.get());
        return ResponseEntity.ok().body("{\"message\": \"Category " + categoryId + " has been deleted.\"}");
    }

}
