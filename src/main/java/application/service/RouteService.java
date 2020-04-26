package application.service;

import application.dao.RouteDAO;
import application.dto.RouteDTO;
import application.dto.RouteMapper;
import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.model.Route;
import application.model.TransportKinds;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RouteService {

    private final RouteMapper routeMapper = new RouteMapper();
    private final RouteDAO routeDAO = new RouteDAO();

    public RouteDTO findById(Long id) throws NoSuchElementException {
        Route route = routeDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return routeMapper.routeToRouteDto(route).orElseThrow(NoSuchElementException::new);
    }

    public void add(RouteDTO routeDTO) throws InsertionFailedException {
        Route route = routeMapper.routeDtoToRoute(routeDTO);
        routeDAO.add(route);
    }

    public void addWithId(RouteDTO routeDTO) throws InsertionFailedException {
        Route route = routeMapper.routeDtoToRoute(routeDTO);
        routeDAO.addWithId(route);
    }

    public void removeById(Long id) throws NoSuchElementException {
        routeDAO.removeById(id);
    }

    public void updateById(Long id, RouteDTO routeDTO) {
        Route route = routeMapper.routeDtoToRoute(routeDTO);
        routeDAO.updateById(id, route);
    }

    public List<RouteDTO> findAll() throws NoSuchElementException {
        return routeMapper.routesToRouteDTOs(routeDAO.findAll());
    }

    public List<RouteDTO> findAllByTransportKind(TransportKinds kind) throws NoSuchElementException {
        return routeMapper.routesToRouteDTOs(routeDAO.findAllByTransportKind(kind));
    }

    public List<RouteDTO> findAllByIds(List<Long> ids) throws NoSuchElementException {
        return routeMapper.routesToRouteDTOs(routeDAO.findAllByIds(ids));
    }

    public boolean contains(RouteDTO routeDTO) {
        return routeDAO.contains(routeMapper.routeDtoToRoute(routeDTO));
    }
}
