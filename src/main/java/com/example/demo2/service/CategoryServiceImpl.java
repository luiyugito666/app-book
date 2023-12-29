package com.example.demo2.service;

import com.example.demo2.dao.ICategoryDao;
import com.example.demo2.model.Category;
import com.example.demo2.response.CategoryResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private static final Logger log= LoggerFactory.getLogger(CategoryServiceImpl.class);
   @Autowired
    private ICategoryDao categoryDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchAllCategory() {

       log.info("Inicio metodo buscarCategorias()");
        CategoryResponseRest response = new CategoryResponseRest();

          try{
              List<Category> category = (List<Category>) categoryDao.findAll();
              response.getCategoryResponse().setCategory(category);
              response.setMetadata("respuesta ok","00","respuesta exitosa");
          }
        catch(Exception e){
            response.setMetadata("respuesta nok","-1","error al consultar categorias");
            log.error("error al consultar categorias",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }



        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchCategoryById(Long id) {

        log.info("inicio metodo bucar categoria por id");

        CategoryResponseRest response = new CategoryResponseRest();

        List<Category> list = new ArrayList<>();
        try {

            Optional<Category> category = categoryDao.findById(id);
            if(category.isPresent()){

                list.add(category.get());
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta ok","00","categoria encontrada");

            }
            else {
                log.error("error al consultar categoria");
                response.setMetadata("Respuesta nok","-1","categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);

            }


        }
        catch(Exception e){
            log.error("error al consultar categoria");
            response.setMetadata("Respuesta nok","-1","error al consultar categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }


        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> createCategory(Category category) {
        log.info("inicio de metodo crear categoria");

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list= new ArrayList<>();

        try {
            Category categorySave = categoryDao.save(category);

            if(categorySave!=null){

                list.add(categorySave);
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("Respuesta ok","00","se creó la categoria");
            }else {
                log.error("error al crear categoria");
                response.setMetadata("Respuesta nok","-1","categoria no creada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);


            }


        }
        catch(Exception e){
            log.error("error al crear categoria");
            response.setMetadata("Respuesta nok","-1","error al crear categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);

        }



        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> editCategory(Category category, Long id) {
        log.info("Inicio de actualizar categoria");

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();
        try {
            Optional<Category> searchCategory= categoryDao.findById(id);

            if(searchCategory.isPresent()){
                searchCategory.get().setName(category.getName());
                searchCategory.get().setDescription(category.getDescription());

                Category updateCategory= categoryDao.save(searchCategory.get());
                if (updateCategory!=null) {
                    list.add(updateCategory);

                    response.getCategoryResponse().setCategory(list);
                    response.setMetadata("Respuesta ok", "00", "categoria actualizada");
                }

                else{
                    log.error("error al actualizar categoria");
                    response.setMetadata("Respuesta nok","-1","categoria no actualizada");
                    return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);

                }

            }else{

                log.error("error al actualizar categoria");
                response.setMetadata("Respuesta nok","-1","categoria no actualizada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){

            log.error("error al editar categoria",e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nok","-1","error al editar categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteCategory(Long id) {

        log.info("Inicio de eliminar categoria");

        CategoryResponseRest response = new CategoryResponseRest();
           try{
            categoryDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "categoria eliminada");

           }
           catch(Exception e){
            log.error("error al eliminar categoria",e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nok","-1","error al eliminar categoria");
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
    }



}
