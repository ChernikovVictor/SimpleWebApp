package application.dao.route;

import application.dao.GenericDAO;
import application.entity.Route;
import application.entity.TransportKinds;

import java.util.List;

public interface RouteDAO extends GenericDAO<Route, Long> {

    List<Route> findByTransportKind(TransportKinds kind);

    List<Route> findByIds(List<Long> ids);

    boolean contains(Route route);

    List<Route> findByCityName(String cityName);

}
