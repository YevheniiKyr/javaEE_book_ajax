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

    private final BookRepository bookRepository;
  //  private final EntityManager entityManager;
    @Transactional
    public Book createBook(final Book book)
    {

     //   return entityManager.merge(book);
        return bookRepository.saveAndFlush(book);
    }
    @Transactional
    public Book getBookById(String isbn )
    {
        //return entityManager.find(Book.class,isbn);
        return bookRepository.getById(isbn);
    }

    public List<Book> getAllBooks() {

       // return entityManager.createQuery("SELECT b FROM Book b" , Book.class).getResultList();
        return bookRepository.findAll();
    }
    @Transactional
    public List<Book> findBookWhereContains(String searchText) {
     /*   return entityManager.createQuery("SELECT u FROM Book u WHERE u.name LIKE :query " +
                        "OR u.author LIKE :query OR u.isbn LIKE :query  ", Book.class)
                .setParameter("query", '%' + searchText + '%')
                .getResultList();
    }*/
      return bookRepository.findAllByNameContainsOrIsbnContains(searchText,searchText);
    }
}
