package com.trangle.bookdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trangle.bookdb.domain.Book;
import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.BookRequest;
import com.trangle.bookdb.domain.Category;
import com.trangle.bookdb.domain.CategoryRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ViewController(BookRepository bookRepository, CategoryRepository categoryRepository){
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository; 
    }

   @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    @RequestMapping(value="/booklists")
    public String bookLists(Model model) {
  	
        model.addAttribute("books", bookRepository.findAll());
        return "booklists";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:../booklists"; //redirect to the booklists mapping, not the html index
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("bookRequest", new BookRequest()); 
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveBook(@ModelAttribute("bookRequest") BookRequest bookRequest){
        Book book = new Book();
    book.setIsbn(bookRequest.getIsbn());
    book.setTitle(bookRequest.getTitle());
    book.setAuthor(bookRequest.getAuthor());
    book.setPublicationYear(bookRequest.getPublicationYear());
    book.setPrice(bookRequest.getPrice());

    // Fetch the Category entity and set it
    Category category = categoryRepository.findById(bookRequest.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
    book.setCategory(category);

    // Save the book entity
    bookRepository.save(book);

    return "redirect:/booklists";

    } 
    
}
