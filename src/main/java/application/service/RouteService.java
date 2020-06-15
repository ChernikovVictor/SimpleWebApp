package application.service;

import application.dao.RouteDAO;
import application.dto.route.RouteDTO;
import application.dto.route.RouteMapper;
import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.entity.Route;
import application.entity.TransportKinds;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RouteService {

    @EJB
    private RouteDAO routeDAO;

    public RouteDTO findById(Long id) throws NoSuchElementException {
        Route route = routeDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return RouteMapper.INSTANCE.routeToRouteDto(route);
    }

    public Long add(RouteDTO routeDTO) throws InsertionFailedException {
        Route route = RouteMapper.INSTANCE.routeDtoToRoute(routeDTO);
        return routeDAO.add(route);
    }

    public void removeById(Long id) throws NoSuchElementException {
        routeDAO.removeById(id);
    }

    public void update(RouteDTO routeDTO) {
        Route route = RouteMapper.INSTANCE.routeDtoToRoute(routeDTO);
        routeDAO.update(route);
    }

    public List<RouteDTO> findAll() {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findAll());
    }

    public List<RouteDTO> findAllByTransportKind(TransportKinds kind) {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findAllByTransportKind(kind));
    }

    public List<RouteDTO> findAllByCityName(String cityName) {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findAllByCityName(cityName));
    }

    public List<RouteDTO> findAllByIds(List<Long> ids) {
        return RouteMapper.INSTANCE.routesToRouteDTOs(routeDAO.findAllByIds(ids));
    }

    public boolean contains(RouteDTO routeDTO) {
        return routeDAO.contains(RouteMapper.INSTANCE.routeDtoToRoute(routeDTO));
    }
}
