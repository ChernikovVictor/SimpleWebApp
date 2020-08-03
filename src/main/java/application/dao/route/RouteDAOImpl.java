package application.dao.route;

import application.dao.GenericDAOImpl;
import application.entity.Route;
import application.entity.TransportKinds;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RouteDAOImpl extends GenericDAOImpl<Route, Long> implements RouteDAO {

    public RouteDAOImpl() {
        super(Route.class);
    }

    @Override
    public List<Route> findByTransportKind(TransportKinds kind) {
        Query query = getEntityManager().createNamedQuery("findByTransportKind").setParameter("kind", kind);
        return query.getResultList();
    }

    @Override
    public List<Route> findByIds(List<Long> ids) {
        return ids.stream().map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Route> findByCityName(String cityName) {
        Query query = getEntityManager().createNamedQuery("findByCityName").setParameter("cityName", cityName);
        return query.getResultList();
    }

    public boolean contains(Route route) {
        Query query = getEntityManager().createNamedQuery("findByIndex").setParameter("index", route.getIndex());
        return CollectionUtils.isNotEmpty(query.getResultList());
    }

}
