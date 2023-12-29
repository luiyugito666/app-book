package com.example.demo2.response;


import com.example.demo2.model.Book;

import java.util.List;

public class BookResponse {
    private List<Book> book;

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
