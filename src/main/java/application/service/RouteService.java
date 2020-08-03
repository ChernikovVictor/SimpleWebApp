package application.service;

import application.dao.route.RouteDAO;
import application.dto.route.RouteDTO;
import application.dto.route.RouteMapper;
import application.exception.NoSuchElementException;
import application.entity.Route;
import application.entity.TransportKinds;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class RouteService {

    @Inject
    private RouteDAO routeDAO;

    public RouteDTO findById(Long id) throws NoSuchElementException {
        Route route = routeDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return RouteMapper.INSTANCE.routeToRouteDto(route);
    }

    @Transactional
    public void makeTransient(Long id) {
        routeDAO.makeTransient(id);
    }

    @Transactional
    public Route makePersistent(RouteDTO routeDTO) {
        Route route = RouteMapper.INSTANCE.routeDtoToRoute(routeDTO);
        return routeDAO.makePersistent(route);
    }

    public List<RouteDTO> findAll() {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findAll());
    }

    public List<RouteDTO> findByTransportKind(TransportKinds kind) {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findByTransportKind(kind));
    }

    public List<RouteDTO> findByCityName(String cityName) {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findByCityName(cityName));
    }

    public List<RouteDTO> findByIds(List<Long> ids) {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findByIds(ids));
    }

    public boolean contains(RouteDTO routeDTO) {
        return routeDAO.contains(RouteMapper.INSTANCE.routeDtoToRoute(routeDTO));
    }
}
