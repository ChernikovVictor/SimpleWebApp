package application.dao;

import application.entity.*;
import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.service.ConnectionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            return new LinkedList<>();
        }
    }

    public List<Route> findAllByTransportKind(TransportKinds kind) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Route> query = session.getNamedQuery("findAllByTransportKind").setParameter("kind", kind);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    public List<Route> findAllByIds(List<Long> ids) {
        return ids.stream().map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public boolean contains(Route route) {
        return this.findById(route.getId()).isPresent();
    }

    public List<Route> findAllByCityName(String cityName) {
        try (Session session = ConnectionManager.getSessionFactory().openSession()) {
            Query<Route> query = session.getNamedQuery("findAllByCityName").setParameter("cityName", cityName);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    public void addWithId(Route route) throws InsertionFailedException {

//       String addRouteSql = "INSERT INTO routes " +
//                "(id, departure_id, destination_id, departure_time, arrival_time, transport_id) " +
//                "VALUES (?, ?, ?, ?, ? ,?);";
//
//        try (Connection connection = ConnectionManager.getConnection();
//                PreparedStatement addStatement = connection.prepareStatement(addRouteSql)) {
//
//            addStatement.setLong(1, route.getId());
//            addStatement.setLong(2, route.getDepartureId());
//            addStatement.setLong(3, route.getDestinationId());
//            addStatement.setString(4, route.getDepartureTime());
//            addStatement.setString(5, route.getArrivalTime());
//            addStatement.setLong(6, route.getTransportId());
//
//            addStatement.executeUpdate();
//        } catch (SQLException | NamingException e) {
//            e.printStackTrace();
//            throw new InsertionFailedException();
//        }
    }
}
