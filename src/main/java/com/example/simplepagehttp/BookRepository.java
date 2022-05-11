package com.example.simplepagehttp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByNameContainsOrIsbnContains(String name, String isbn);

}
