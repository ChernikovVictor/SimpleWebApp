package application.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAOImpl<T, ID extends Serializable>
        implements GenericDAO<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;
    private final Class<T> entityClass;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
        query.select(query.from(entityClass));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public T makePersistent(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void makeTransient(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void makeTransient(ID id) {
        T entity = entityManager.find(entityClass, id);
        makeTransient(entity);
    }
}
