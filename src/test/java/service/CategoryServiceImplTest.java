package service;

import com.example.demo2.dao.ICategoryDao;
import com.example.demo2.model.Category;
import com.example.demo2.response.CategoryResponseRest;
import com.example.demo2.service.CategoryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
    @InjectMocks
    CategoryServiceImpl service;
    @Mock
    ICategoryDao categoryDao;
    List<Category> list =new ArrayList<>();
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.loadCategory();

    }

    @Test
    public void searchAllCategoryTest(){
        when(categoryDao.findAll()).thenReturn(list);

        ResponseEntity<CategoryResponseRest> response = service.searchAllCategory();

        assertEquals(4,response.getBody().getCategoryResponse().getCategory().size());
        verify(categoryDao, times(1)).findAll();

            }
    public void loadCategory(){
        Category cat1 =new Category(Long.valueOf(1),"bookA","all book type A");
        Category cat2 =new Category(Long.valueOf(1),"bookB","all book type B");
        Category cat3 =new Category(Long.valueOf(1),"bookC","all book type C");
        Category cat4 =new Category(Long.valueOf(1),"bookD","all book type D");

        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        list.add(cat4);

    }

}