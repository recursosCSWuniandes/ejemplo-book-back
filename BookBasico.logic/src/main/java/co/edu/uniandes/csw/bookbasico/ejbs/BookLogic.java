package co.edu.uniandes.csw.bookbasico.ejbs;

import co.edu.uniandes.csw.bookbasico.api.IBookLogic;
import co.edu.uniandes.csw.bookbasico.entities.BookEntity;
import co.edu.uniandes.csw.bookbasico.persistence.BookPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BookLogic implements IBookLogic {

    @Inject
    private BookPersistence persistence;

    

    @Override
    public List<BookEntity> getBooks() {
        return persistence.findAll();
    }

    @Override
    public BookEntity getBook(Long id) {
        return persistence.find(id);
    }

    @Override
    public BookEntity createBook(BookEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public BookEntity updateBook(BookEntity entity) {
        BookEntity newEntity = entity;
        BookEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteBook(Long id) {
        persistence.delete(id);
    }

}
