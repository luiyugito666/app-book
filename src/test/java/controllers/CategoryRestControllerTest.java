package controllers;

import com.example.demo2.controller.CategoryRestController;
import com.example.demo2.model.Category;
import com.example.demo2.response.CategoryResponseRest;
import com.example.demo2.service.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryRestControllerTest {
    @InjectMocks
    CategoryRestController categoryController;

    @Mock
    private ICategoryService service;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);


    }

    @Test
    public void createTest(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Category category = new Category();

        when(service.createCategory(any(Category.class)))
                .thenReturn(new ResponseEntity<CategoryResponseRest>(HttpStatus.OK));

        ResponseEntity<CategoryResponseRest> response = categoryController.createCategory(category);

       assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


}
