package co.edu.uniandes.csw.bookbasico.persistence;

import co.edu.uniandes.csw.bookbasico.entities.BookEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @generated
 */
@Stateless
public class BookPersistence extends CrudPersistence<BookEntity> {

    @PersistenceContext
    private EntityManager em;

   

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<BookEntity> getEntityClass() {
        return BookEntity.class;
    }
}
