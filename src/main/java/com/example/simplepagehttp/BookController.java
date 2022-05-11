package com.example.simplepagehttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    //List<Book> bookList = new ArrayList<>();

    private List<Book> bookListFiltered(String str) {
        return bookService.getAllBooks()
                .stream()
                .filter(book -> book.getName().contains(str) || book.getIsbn().contains(str))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/books", method = {RequestMethod.GET})
    public String addBookForm() {
        return "addBookPage";
    }

    @RequestMapping(value = "/books", method = {RequestMethod.POST})
    public ResponseEntity addBook(@RequestBody final Book book) {

     //   bookList.add(book);
        bookService.createBook(book);
        System.out.println(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

    @GetMapping("/all_books")
    public ResponseEntity<List<Book>> allBooks(@RequestParam(name = "find", required = false) final String filterString) {
        System.out.println("str" + filterString);
        List<Book> bookList = bookService.getAllBooks();
        if (filterString == null) {
            return ResponseEntity.ok().body(bookList);
        } else {
           // return ResponseEntity.ok().body(bookListFiltered(filterString));
            return ResponseEntity.ok().body(bookService.findBookWhereContains(filterString));
        }

    }
    @GetMapping("/book/{isbn}")
    public String getBookById(Model model, @PathVariable(name = "isbn") final String isbn) {

        Book book = bookService.getBookById(isbn);
        if(book==null){
            return "BookDoesntExist";
        }

        model.addAttribute( "book",book);
        return "BookByIdPage";

    }
}

