package com.example.simplepagehttp;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter


public class Book {
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Id
    @Column(name = "isbn")
    private String isbn;



    public Book(String name, String author, String isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                '}';
    }
}
