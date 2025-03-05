package com.trangle.bookdb.web;

import org.springframework.web.bind.annotation.RestController;

import com.trangle.bookdb.domain.Book;
import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.BookRequest;
import com.trangle.bookdb.domain.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api") 
public class BookController {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

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

    //endpoint to show all books
    @GetMapping("/books")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //endpoint to get books by title
    @GetMapping("/books/title/{title}")
    public @ResponseBody ResponseEntity<List<Book>> getBooksByTitle(@PathVariable("title") String title) {
        System.out.println("Received title: " + title);

        List<Book> books = bookRepository.findByTitle(title);

        if (books.isEmpty()){
            throw new ResourceNotFoundException("No book found with title: " + title); 
        }
        
        return ResponseEntity.ok(books); 
    }

    //endpoint to add new book
    //we use a Data Transfer Object called BookRequest to handle adding category 
    @PostMapping("/books")
    public @ResponseBody ResponseEntity<Book> addNewBook(@RequestBody BookRequest bookRequest) {
        Category category = categoryRepository.findById(bookRequest.getCategoryId()).
                            orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " + bookRequest.getCategoryId() ));

        Book book = new Book(
            bookRequest.getIsbn(),
            bookRequest.getTitle(),
            bookRequest.getAuthor(),
            bookRequest.getPublicationYear(),
            bookRequest.getPrice(),
            category
        );
    
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }
    
    //endpoint for edit existing book
    @PutMapping("/books/edit/{id}")
    public @ResponseBody ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Category category = categoryRepository.findById(bookRequest.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existingBook.setIsbn(bookRequest.getIsbn());
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());      
        existingBook.setPublicationYear(bookRequest.getPublicationYear());
        existingBook.setPrice(bookRequest.getPrice());
        existingBook.setCategory(category);

        bookRepository.save(existingBook); 

        return ResponseEntity.ok(existingBook);
    }
}
