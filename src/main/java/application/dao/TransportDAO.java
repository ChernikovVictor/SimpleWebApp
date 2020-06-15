package application.dao;

import application.entity.*;
import application.service.ConnectionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class TransportDAO {

    public Optional<Transport> findById(Long id) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Transport.class, id));
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Transport> findAll() {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Transport> query = session.createQuery("from Transport");
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
