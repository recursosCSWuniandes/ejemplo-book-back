package co.edu.uniandes.csw.bookbasico.persistence;

import co.edu.uniandes.csw.bookbasico.entities.EditorialEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EditorialPersistence {

    @PersistenceContext(unitName = "BookBasicoPU")
    protected EntityManager em;

    public EditorialEntity create(EditorialEntity entity) {
        em.persist(entity);
        return entity;
    }

    public EditorialEntity update(EditorialEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        EditorialEntity entity = em.find(EditorialEntity.class, id);
        em.remove(entity);
    }

    public EditorialEntity find(Long id) {
        return em.find(EditorialEntity.class, id);
    }

    public List<EditorialEntity> findAll() {
        Query q = em.createQuery("select u from EditorialEntity u");
        return q.getResultList();
    }
}
