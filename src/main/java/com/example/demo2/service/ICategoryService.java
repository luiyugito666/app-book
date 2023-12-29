package com.example.demo2.service;

import com.example.demo2.model.Category;
import com.example.demo2.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    public ResponseEntity<CategoryResponseRest> searchAllCategory();

     public ResponseEntity<CategoryResponseRest> searchCategoryById(Long id);

   public  ResponseEntity<CategoryResponseRest> createCategory(Category category);
    public  ResponseEntity<CategoryResponseRest> editCategory(Category category, Long id);

   public ResponseEntity<CategoryResponseRest> deleteCategory(Long id);
}
