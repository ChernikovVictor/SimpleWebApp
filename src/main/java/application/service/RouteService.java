package application.service;

import application.dao.RouteDAO;
import application.dto.route.RouteDTO;
import application.dto.route.RouteMapper;
import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.entity.Route;
import application.entity.TransportKinds;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RouteService {

    private final RouteMapper routeMapper = new RouteMapper();
    private final RouteDAO routeDAO = new RouteDAO();

    public RouteDTO findById(Long id) throws NoSuchElementException {
        Route route = routeDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return routeMapper.routeToRouteDto(route);
    }

    public Long add(RouteDTO routeDTO) throws InsertionFailedException {
        Route route = routeMapper.routeDtoToRoute(routeDTO);
        return routeDAO.add(route);
    }

    public void removeById(Long id) throws NoSuchElementException {
        routeDAO.removeById(id);
    }

    public void update(RouteDTO routeDTO) {
        Route route = routeMapper.routeDtoToRoute(routeDTO);
        routeDAO.update(route);
    }

    public List<RouteDTO> findAll() {
        return routeMapper.routesToRouteDTOs(routeDAO.findAll());
    }

    public List<RouteDTO> findAllByTransportKind(TransportKinds kind) {
        return routeMapper.routesToRouteDTOs(routeDAO.findAllByTransportKind(kind));
    }

    public List<RouteDTO> findAllByCityName(String cityName) {
        return routeMapper.routesToRouteDTOs(routeDAO.findAllByCityName(cityName));
    }

    public List<RouteDTO> findAllByIds(List<Long> ids) {
        return routeMapper.routesToRouteDTOs(routeDAO.findAllByIds(ids));
    }

    public boolean contains(RouteDTO routeDTO) {
        return routeDAO.contains(routeMapper.routeDtoToRoute(routeDTO));
    }
}
