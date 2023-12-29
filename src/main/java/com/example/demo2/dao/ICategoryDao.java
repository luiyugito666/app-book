package com.example.demo2.dao;

import com.example.demo2.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category,Long>
{
}
