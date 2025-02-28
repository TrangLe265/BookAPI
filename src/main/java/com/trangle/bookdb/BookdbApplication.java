package com.trangle.bookdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; //for debugging

import org.springframework.boot.CommandLineRunner; //a SpringBoot interface allows excutation of additional code before the application starts


import com.trangle.bookdb.domain.Book;
import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.CategoryRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication// a combination of EnableConfiguration, ComponentScan, Configuration
public class BookdbApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
		BookdbApplication.class
	); 

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	public BookdbApplication(BookRepository bookRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run
			(BookdbApplication.class, args);
		logger.info("Application started");
	}
	

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application running");

		categoryRepository.save(new Category("Fiction"));
		categoryRepository.save(new Category("Education"));
		categoryRepository.save(new Category("Romance"));
		categoryRepository.save(new Category("Cooking"));

		bookRepository.save(new Book("978-3-16-148410-0", "To Kill a Mockingbird", "Harper Lee", 1960, 281, categoryRepository.findByCategoryName("Fiction").get(0)));
		bookRepository.save(new Book("978-1-23-456789-7", "A Brief History of Time", "Stephen Hawking", 1988, 212, categoryRepository.findByCategoryName("Education").get(0)));
		bookRepository.save(new Book("978-0-12-345678-9", "1984", "George Orwell", 1949, 328, categoryRepository.findByCategoryName("Fiction").get(0)));
		bookRepository.save(new Book("978-9-87-654321-0", "Pride and Prejudice", "Jane Austen", 1813, 279, categoryRepository.findByCategoryName("Romance").get(0)));
		bookRepository.save(new Book("978-8-76-543210-1", "Mastering the Art of French Cooking", "Julia Child", 1961, 726, categoryRepository.findByCategoryName("Cooking").get(0)));
		bookRepository.save(new Book("978-7-65-432109-8", "The Great Gatsby", "F. Scott Fitzgerald", 1925, 180 ));

		for (Book book : bookRepository.findAll()) {
			logger.info(book.toString());
		}; 
	}

}
