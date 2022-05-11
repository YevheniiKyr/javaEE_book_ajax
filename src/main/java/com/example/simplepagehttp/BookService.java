package com.example.simplepagehttp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;
    @Transactional
    public Book createBook(final Book book)
    {
     //   System.out.println(book);
       // return book;
        return entityManager.merge(book);
    }
    @Transactional
    public Book getBookById(String isbn )
    {
        return entityManager.find(Book.class,isbn);
    }

    public List<Book> getAllBooks() {
      //  List<Book> books =  new ArrayList<Book>();
      //  books.add(new Book("dd","dd","dd"));
      //  return books ;
        return entityManager.createQuery("SELECT b FROM Book b" , Book.class).getResultList();
    }
    @Transactional
    public List<Book> findBookWhereContains(String searchText) {
        return entityManager.createQuery("SELECT u FROM Book u WHERE u.name LIKE :query " +
                        "OR u.author LIKE :query OR u.isbn LIKE :query  ", Book.class)
                .setParameter("query", '%' + searchText + '%')
                .getResultList();
    }

}
