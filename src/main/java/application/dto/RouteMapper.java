package application.dto;

import application.dao.CityDAO;
import application.dao.TransportDAO;
import application.exception.NoSuchElementException;
import application.model.City;
import application.model.Route;
import application.model.Transport;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RouteMapper {

    public Optional<RouteDTO> routeToRouteDto (Route route) {

        City departure;
        City destination;
        Transport transport;
        try {
            departure = (new CityDAO()).findById(route.getDepartureId()).orElseThrow(NoSuchElementException::new);
            destination = (new CityDAO()).findById(route.getDestinationId()).orElseThrow(NoSuchElementException::new);
            transport = (new TransportDAO()).findById(route.getTransportId()).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }

      return Optional.ofNullable(RouteDTO.builder()
                .id(route.getId())
                .departure(departure)
                .destination(destination)
                .departureTime(route.getDepartureTime())
                .arrivalTime(route.getArrivalTime())
                .transport(transport)
                .build());
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

    public List<RouteDTO> routesToRouteDTOs(List<Route> routes) throws NoSuchElementException {

        List<Optional<RouteDTO>> routeDTOs = routes.stream()
                .map(this::routeToRouteDto)
                .filter(Optional::isPresent)
                .collect(Collectors.toList());

        if (routeDTOs.size() != routes.size()) {
            throw new NoSuchElementException();
        }

        return routeDTOs.stream().map(Optional::get).collect(Collectors.toCollection(LinkedList::new));
      }
}
