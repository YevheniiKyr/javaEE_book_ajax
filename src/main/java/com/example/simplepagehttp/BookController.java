package com.example.simplepagehttp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    List<Book> bookList = new ArrayList<>();

    private List<Book> bookListFiltered(String str) {
        return bookList
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

        bookList.add(book);
        System.out.println(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

    @GetMapping("/all_books")
    public ResponseEntity<List<Book>> allBooks(@RequestParam(name = "find", required = false) final String filterString) {
        System.out.println("str" + filterString);
        if (filterString == null) {
            return ResponseEntity.ok().body(bookList);
        } else {
            return ResponseEntity.ok().body(bookListFiltered(filterString));
        }

    }
}

