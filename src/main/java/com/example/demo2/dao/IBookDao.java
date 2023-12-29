package com.example.demo2.dao;

import com.example.demo2.model.Book;
import org.springframework.data.repository.CrudRepository;





public interface IBookDao extends CrudRepository<Book,Long> {
}
