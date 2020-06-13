package application.dto.city;

import application.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO cityToCityDto(City city);

    City cityDtoToCity(CityDTO cityDTO);

    default List<CityDTO> citiesToCityDTOs(List<City> cities) {
        return cities.stream()
                .map(this::cityToCityDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
