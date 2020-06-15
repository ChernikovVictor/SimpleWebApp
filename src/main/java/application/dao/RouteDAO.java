package application.dao;

import application.entity.*;
import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.service.ConnectionManager;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class RouteDAO {

    public Optional<Route> findById(Long id) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Route.class, id));
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Long add(Route route) throws InsertionFailedException {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(route);
            transaction.commit();
            return id;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new InsertionFailedException();
        }
    }

    public void removeById(Long id) throws NoSuchElementException {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {

            Route route = this.findById(id).orElseThrow(NoSuchElementException::new);

            Transaction transaction = session.beginTransaction();
            session.delete(route);
            transaction.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void update(Route route) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(route);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Route> findAll() {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Route> query = session.createQuery("from Route");
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Route> findAllByTransportKind(TransportKinds kind) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Route> query = session.getNamedQuery("findAllByTransportKind").setParameter("kind", kind);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Route> findAllByIds(List<Long> ids) {
        return ids.stream().map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean contains(Route route) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Route> query = session.getNamedQuery("findByIndex").setParameter("index", route.getIndex());
            return CollectionUtils.isNotEmpty(query.list());
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Route> findAllByCityName(String cityName) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Route> query = session.getNamedQuery("findAllByCityName").setParameter("cityName", cityName);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
