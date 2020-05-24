package application.dao;

import application.entity.City;
import application.service.ConnectionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityDAO {

    public Optional<City> findById(Long id) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(City.class, id));
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<City> findAll() {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<City> query = session.createQuery("from City");
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}
