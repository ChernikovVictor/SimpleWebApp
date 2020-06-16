package application.dto.route;

import application.dto.city.CityDTO;
import application.dto.transport.TransportDTO;
import application.entity.City;
import application.entity.Route;
import application.entity.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    RouteDTO routeToRouteDto (Route route);

    Route routeDtoToRoute(RouteDTO routeDTO);

    default List<RouteDTO> routesToRouteDTOs(List<Route> routes) {
        return routes.stream()
                .map(this::routeToRouteDto)
                .collect(Collectors.toCollection(LinkedList::new));
      }

    default List<Route> routesDTOsToRoutes(List<RouteDTO> routeDTOs) {
        return routeDTOs.stream()
                .map(this::routeDtoToRoute)
                .collect(Collectors.toCollection(LinkedList::new));
      }

    CityDTO cityToCityDto(City city);

    City cityDtoToCity(CityDTO cityDTO);

    TransportDTO transportToTransportDto(Transport transport);

    Transport transportDtoToTransport(TransportDTO transportDTO);
}
