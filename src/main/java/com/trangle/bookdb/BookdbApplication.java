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

		bookRepository.save(new Book("978-3-16-148410-0", "Book One", "Author One", 2001, 300, categoryRepository.findByCategoryName("Fiction").get((0))));
		bookRepository.save(new Book("978-1-23-456789-7", "Book Two", "Author Two", 2005, 250, categoryRepository.findByCategoryName("Education").get(0)));
		bookRepository.save(new Book("978-0-12-345678-9", "Book Three", "Author Three", 2010, 400, categoryRepository.findByCategoryName("Fiction").get(0)));
		bookRepository.save(new Book("978-9-87-654321-0", "Book Four", "Author Four", 2015, 350, categoryRepository.findByCategoryName("Romance").get(0)));
		bookRepository.save(new Book("978-8-76-543210-1", "Book Five", "Author Five", 2020, 450, categoryRepository.findByCategoryName("Cooking").get(0)));

		for (Book book : bookRepository.findAll()) {
			logger.info(book.toString());
		}; 
	}

}
