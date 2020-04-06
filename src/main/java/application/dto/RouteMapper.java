package application.dto;

import application.dao.CityDAO;
import application.dao.TransportDAO;
import application.exception.NoSuchElementException;
import application.model.City;
import application.model.Route;
import application.model.Transport;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteMapper {

    public RouteDTO routeToRouteDto (Route route) {

        City departure = null;
        City destination = null;
        Transport transport = null;
        try {
            departure = (new CityDAO()).findById(route.getDepartureId());
            destination = (new CityDAO()).findById(route.getDestinationId());
            transport = (new TransportDAO()).findById(route.getTransportId());
        } catch (NoSuchElementException ignored) { /* Never caught */ }

        return RouteDTO.builder()
                .id(route.getId())
                .departure(departure)
                .destination(destination)
                .departureTime(route.getDepartureTime())
                .arrivalTime(route.getArrivalTime())
                .transport(transport)
                .build();
    }

    public Route routeDtoToRoute(RouteDTO routeDTO) {
        return Route.builder()
                .id(routeDTO.getId())
                .departureId(routeDTO.getDeparture().getId())
                .destinationId(routeDTO.getDestination().getId())
                .departureTime(routeDTO.getDepartureTime())
                .arrivalTime(routeDTO.getArrivalTime())
                .transportId(routeDTO.getTransport().getId())
                .build();
    }

    public List<RouteDTO> routesToRouteDTOs(List<Route> routes) {
        return routes.stream().map(this::routeToRouteDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
