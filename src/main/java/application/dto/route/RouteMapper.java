package application.dto.route;

import application.entity.Route;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteMapper {

    public RouteDTO routeToRouteDto (Route route) {
        return RouteDTO.builder()
                .id(route.getId())
                .departure(route.getDeparture())
                .destination(route.getDestination())
                .departureTime(route.getDepartureTime())
                .arrivalTime(route.getArrivalTime())
                .transport(route.getTransport())
                .build();
    }

    public Route routeDtoToRoute(RouteDTO routeDTO) {
        return Route.builder()
                .id(routeDTO.getId())
                .departure(routeDTO.getDeparture())
                .destination(routeDTO.getDestination())
                .departureTime(routeDTO.getDepartureTime())
                .arrivalTime(routeDTO.getArrivalTime())
                .transport(routeDTO.getTransport())
                .build();
    }

    public List<RouteDTO> routesToRouteDTOs(List<Route> routes) {
        return routes.stream()
                .map(this::routeToRouteDto)
                .collect(Collectors.toCollection(LinkedList::new));
      }

    public List<Route> routesDTOsToRoutes(List<RouteDTO> routeDTOs) {
        return routeDTOs.stream()
                .map(this::routeDtoToRoute)
                .collect(Collectors.toCollection(LinkedList::new));
      }
}
