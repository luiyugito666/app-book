package com.example.demo2.controller;

import com.example.demo2.model.Category;
import com.example.demo2.response.CategoryResponseRest;
import com.example.demo2.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {
    @Autowired
    private ICategoryService service;

    @GetMapping("/category")
    public ResponseEntity<CategoryResponseRest>  consultCat(){

        ResponseEntity<CategoryResponseRest> response = service.searchAllCategory();
        return response;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseRest> consultCatById(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response=service.searchCategoryById(id);
        return response;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseRest> createCategory(@RequestBody Category request){

        ResponseEntity<CategoryResponseRest> response =service.createCategory(request);
        return response;
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryResponseRest> editCategory(@RequestBody Category request,@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response = service.editCategory(request,id);
    return response;
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<CategoryResponseRest> deleteCategory(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response=service.deleteCategory(id);
        return response;

    }
}
