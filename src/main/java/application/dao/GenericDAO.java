package application.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID extends Serializable> extends Serializable {

    Optional<T> findById(ID id);

    List<T> findAll();

    T makePersistent(T entity);

    void makeTransient(T entity);

    void makeTransient(ID id);
}
