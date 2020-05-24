package application.service;

import application.dao.CityDAO;
import application.dto.city.CityDTO;
import application.dto.city.CityMapper;
import application.entity.City;
import application.exception.NoSuchElementException;

import java.util.List;

public class CityService {

    private final CityDAO cityDAO = new CityDAO();
    private final CityMapper cityMapper = new CityMapper();

    public CityDTO findById(Long id) throws NoSuchElementException {
        City city = cityDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return cityMapper.cityToCityDto(city);
    }

    public List<CityDTO> findAll() {
        return cityMapper.citiesToCityDTOs(cityDAO.findAll());
    }
}
