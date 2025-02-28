package com.trangle.bookdb.web;

import org.springframework.web.bind.annotation.RestController;

import com.trangle.bookdb.domain.Book;
import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.CategoryRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public class ResourceNotFoundException extends RuntimeException {
		public ResourceNotFoundException(String message) {
			super(message);
		}
	}

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookRepository.findById(id).orElseThrow();

        existingBook.setIsbn(book.getIsbn());
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());      
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setPrice(book.getPrice());

        Category category = categoryRepository.findById(book.getCategory().getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + book.getCategory().getCategoryId()));
        
        existingBook.setCategory(category);

        bookRepository.save(existingBook); 

        return ResponseEntity.ok(existingBook);
    }
}
