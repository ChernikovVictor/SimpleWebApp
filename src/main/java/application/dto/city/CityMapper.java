package application.dto.city;

import application.entity.City;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CityMapper {

    public CityDTO cityToCityDto(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .station(city.getStation())
                .routes(city.getRoutes())
                .build();
    }

    public City cityDtoToCity(CityDTO cityDTO) {
        return City.builder()
                .id(cityDTO.getId())
                .name(cityDTO.getName())
                .station(cityDTO.getStation())
                .routes(cityDTO.getRoutes())
                .build();
    }

    public List<CityDTO> citiesToCityDTOs(List<City> cities) {
        return cities.stream()
                .map(this::cityToCityDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
