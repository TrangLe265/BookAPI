package com.trangle.bookdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; //for debugging

import org.springframework.boot.CommandLineRunner; //a SpringBoot interface allows excutation of additional code before the application starts


import com.trangle.bookdb.domain.Book;
import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.CategoryRepository;
import com.trangle.bookdb.domain.AppUser;
import com.trangle.bookdb.domain.AppUserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication// a combination of EnableConfiguration, ComponentScan, Configuration
public class BookdbApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
		BookdbApplication.class
	); 

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;
	private final AppUserRepository userRepository; 

	public BookdbApplication(BookRepository bookRepository, CategoryRepository categoryRepository, AppUserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository; 
	}
	
	public static void main(String[] args) {
		SpringApplication.run
			(BookdbApplication.class, args);
		logger.info("Application started");
	}
	

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application running");

		Category fiction = new Category("Fiction"); 
		Category education = new Category("Education"); 
		Category romance = new Category("Romance"); 
		Category cooking = new Category("Cooking"); 
		categoryRepository.save(fiction);
		categoryRepository.save(education);
		categoryRepository.save(cooking);
		categoryRepository.save(romance);
		
		bookRepository.save(new Book("978-3-16-148410-0", "a", "Harper Lee", 1960, 281,fiction));
		bookRepository.save(new Book("978-1-23-456789-7", "A Brief History of Time", "Stephen Hawking", 1988, 212, education));
		bookRepository.save(new Book("978-0-12-345678-9", "1984", "George Orwell", 1949, 328, romance));
		bookRepository.save(new Book("978-9-87-654321-0", "Pride and Prejudice", "Jane Austen", 1813, 279, romance));
		bookRepository.save(new Book("978-8-76-543210-1", "Mastering the Art of French Cooking", "Julia Child", 1961, 726, cooking));
	

		for (Book book : bookRepository.findAll()) {
			logger.info(book.toString());
		}; 

		userRepository.save(new AppUser("user", "$2a$10$rons4ZzyBFDGqWPwPMlwVOwSO1aUt1axhzZHt/n5m.1.CAGmbo9Tq", "USER")); 
		userRepository.save(new AppUser("admin","$2a$10$T48fBZgZBsMgmG0PBD8bMeSJXRV0ubYiBSL/wJX7l8GkwlqaM1XLO", "ADMIN")); 

	}

}
