package com.example.demo2.controller;

import com.example.demo2.model.Book;
import com.example.demo2.response.BookResponseRest;
import com.example.demo2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/v1")
public class BookRestController {
    @Autowired
    private IBookService service;

    @GetMapping("/book")
    public ResponseEntity<BookResponseRest> getAllBooks(){
        ResponseEntity<BookResponseRest> response = service.getAllBooks();
        return response;

    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseRest> getBookById(@PathVariable Long id) {
        ResponseEntity<BookResponseRest> response=service.getBookById(id);
        return response;

    }
    @PostMapping("/book")
    public ResponseEntity<BookResponseRest> createBook(@RequestBody Book book){
        ResponseEntity<BookResponseRest> response = service.createBook(book);
        return response;

    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookResponseRest> editBook(@RequestBody Book book, @PathVariable Long id){
        ResponseEntity<BookResponseRest> response=service.editBook(book,id);
        return response;

    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<BookResponseRest> deleteBook(@PathVariable Long id){

        ResponseEntity<BookResponseRest> response=service.deleteBook(id);
        return response;
    }


}
