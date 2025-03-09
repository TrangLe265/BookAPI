package com.trangle.bookdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trangle.bookdb.domain.Book;
import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class ViewController {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

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
        model.addAttribute("book", new Book());
        model.addAttribute("category", categoryRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveBook(Book book){
        bookRepository.save(book); 
        return "redirect:../booklists"; 
    }
    
    
    
    

}
