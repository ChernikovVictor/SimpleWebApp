package application.dao;

import application.entity.*;
import application.service.ConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Stateless
public class TransportDAO {

    public Optional<Transport> findById(Long id) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Transport.class, id));
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public List<Transport> findAll() {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Transport> query = session.createQuery("from Transport");
            return query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
