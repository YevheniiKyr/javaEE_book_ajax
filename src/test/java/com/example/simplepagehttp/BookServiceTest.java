package com.example.simplepagehttp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired BookService bookService;

    @Test
    @Sql("/BookService/initBook.sql")
    void shouldGetBookById() {
        assertThat(bookService.getBookById("1"))
                .returns("1",Book::getIsbn)
                .returns("Me", Book::getAuthor);
    }

    @Test
    void shouldCreateBook() {
        assertThat(bookService.createBook(new Book("myBook","author2","7")))
                .returns("7",Book::getIsbn)
                .returns("author2", Book::getAuthor)
                .returns("myBook", Book::getName);
    }

    @Test
    @Sql("/BookService/clean.sql")
    @Sql("/BookService/initBook.sql")
    void findAllBook() {
        assertThat(bookService.getAllBooks()).
                asList().hasSize(4);
        bookService.createBook(new Book("myBook","author2","7"));
        assertThat(bookService.getAllBooks()).
                asList().hasSize(5);
    }

    @Test
    @Sql("/BookService/clean.sql")
    @Sql("/BookService/initBook.sql")
    void findBookFiltered() {
        assertThat(bookService.findBookWhereContains("name")).
                asList().hasSize(4);
        assertThat(bookService.findBookWhereContains("name2")).
                asList().hasSize(1);
        assertThat(bookService.findBookWhereContains("2")).
                asList().hasSize(1);

    }



}