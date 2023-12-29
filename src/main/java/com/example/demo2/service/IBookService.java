package com.example.demo2.service;

import com.example.demo2.model.Book;
import com.example.demo2.model.Category;
import com.example.demo2.response.BookResponseRest;
import com.example.demo2.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;


public interface IBookService {
    public ResponseEntity<BookResponseRest> getAllBooks();
    public ResponseEntity<BookResponseRest>getBookById(Long id);

    public  ResponseEntity<BookResponseRest> createBook(Book book);
    public  ResponseEntity<BookResponseRest> editBook(Book book, Long id);

    public ResponseEntity<BookResponseRest> deleteBook(Long id);
}
