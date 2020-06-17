package application.dao;

import application.entity.City;
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
public class CityDAO {

    public Optional<City> findById(Long id) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(City.class, id));
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public List<City> findAll() {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<City> query = session.createQuery("from City");
            return query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
