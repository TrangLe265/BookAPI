package com.trangle.bookdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trangle.bookdb.domain.BookRepository;
import com.trangle.bookdb.domain.CategoryRepository;

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
	
	
    @RequestMapping(value="/books")
    public String bookLists(Model model) {
  	
        model.addAttribute("books", bookRepository.findAll());
        return "booklists";
    }//

}
