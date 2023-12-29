package com.example.demo2.service;

import com.example.demo2.dao.IBookDao;
import com.example.demo2.model.Book;
import com.example.demo2.response.BookResponseRest;
import jakarta.transaction.TransactionScoped;
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
public class BookServiceImpl implements IBookService{


    private static final Logger log= LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private IBookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<BookResponseRest> getAllBooks() {
        log.info("inicio metodo buscar libros");
        BookResponseRest response = new BookResponseRest();

        try {

            List<Book> list= (List<Book>) bookDao.findAll();
            response.getBookResponse().setBook(list);
            response.setMetadata("respuesta ok","00","respuesta exitosa");

        }
        catch(Exception e){
            log.error("Error al consultar libros",e.getMessage());
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","error al consultar libros");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> getBookById(Long id) {
        log.info("inicio del metodo obtener por id");

        BookResponseRest response = new BookResponseRest();
        List<Book> list = new ArrayList<>();

        try {
            Optional<Book> book = bookDao.findById(id);
            if(book.isPresent()){
                list.add(book.get());
                response.getBookResponse().setBook(list);
                response.setMetadata("respuesta ok","00","respuesta exitosa");

            }
            else {
                log.error("error al buscar libro");
                response.setMetadata("respuesta nok","-1","error al buscar libro");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.NOT_FOUND);
               }

        }
        catch(Exception e){
            log.error("error al buscar libro",e.getMessage());
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","error al buscar libro");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);



        }



        return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> createBook(Book book) {
        log.info("inicio del metodo crear libro");
            BookResponseRest response=new BookResponseRest();
            List<Book> list = new ArrayList<>();
            try {

                Book bookCreate=bookDao.save(book);

                if(bookCreate!=null){
                    list.add(bookCreate);
                    response.getBookResponse().setBook(list);
                    response.setMetadata("respuesta ok","00","respuesta exitosa");

                }
                else {
                    log.error("error al crear libro");
                    response.setMetadata("respuesta nok","-1","error al crear libro");
                    return new ResponseEntity<BookResponseRest>(response,HttpStatus.BAD_REQUEST);

                }

            }
            catch  (Exception e){
                log.error("error al buscar libro",e.getMessage());
                e.getStackTrace();
                response.setMetadata("respuesta nok","-1","error al buscar libro");
                return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }



       return new ResponseEntity<BookResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> editBook(Book book, Long id) {
        log.info("Inicio del metodo editar libro");

        BookResponseRest response = new BookResponseRest();
        List<Book> list=new ArrayList<>();
        try {

           Optional<Book>  bookById= bookDao.findById(id);

           if(bookById.isPresent()){
               bookById.get().setName(book.getName());
               bookById.get().setDescription(book.getDescription());
               bookById.get().setCategory(book.getCategory());

               Book updateBook=bookDao.save(book);

               if(updateBook!=null){

                   list.add(updateBook);
                   response.getBookResponse().setBook(list);
                   response.setMetadata("respuesta ok","00","libro actualizado correctamente");

               }else{
                   log.error("error al actualizar libro");
                   response.setMetadata("respuesta nok","-1","error al actualizar libro");
                   return new ResponseEntity<BookResponseRest>(response,HttpStatus.BAD_REQUEST);
               }


           }
           else{
               log.error("error al actualizar libro");
               response.setMetadata("respuesta nok","-1","error al actualizar libro");
               return new ResponseEntity<BookResponseRest>(response,HttpStatus.NOT_FOUND);
           }

        }
        catch(Exception e){
            log.error("error al actualizar libro",e.getMessage());
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","error al actualizar libro");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);


        }




            return new ResponseEntity<BookResponseRest>(response,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<BookResponseRest> deleteBook(Long id) {

            log.info("inicio del metodo eliminar libro");

            BookResponseRest response=new BookResponseRest();

            try {
                bookDao.deleteById(id);
                response.setMetadata("Respuesta ok", "00", "categoria eliminada");



            }

          catch(Exception e){
            log.error("error al eliminar libro",e.getMessage());
            e.getStackTrace();
            response.setMetadata("respuesta nok","-1","error al eliminar libro");
            return new ResponseEntity<BookResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);


        }

        return new ResponseEntity<BookResponseRest>(response,HttpStatus.OK);
    }
}
