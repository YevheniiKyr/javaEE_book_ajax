package com.example.simplepagehttp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    List<Book> bookList = new ArrayList<>();

    @RequestMapping(value = "/books",method = {RequestMethod.GET})
    public String addBookForm(){
        //Model model;
       // model.addAttribute(book);
      return "addBookPage";
    }
    @RequestMapping(value = "/books",method = {RequestMethod.POST})
    public String addBook(@ModelAttribute Book book){
        bookList.add(book);
        System.out.println(book);
        //Model model;
        // model.addAttribute("book" , book);
          return "addBookPage";
    }

    @RequestMapping(value = "/bookList",method = {RequestMethod.GET})
    public String seeBooks(Model model){


  //<input type = "button" value = "See all" onClick ='location.href="https://localhost:8080/allbooks"' />
        model.addAttribute("bookList" , bookList);
        return "allBooksPage";
    }

    @RequestMapping(value = "/allBooks",method = {RequestMethod.GET})
    public String seeBooksRedirect(Model model)
    {
        System.out.println("ssss");
        return "redirect:/bookList";
    }
}
