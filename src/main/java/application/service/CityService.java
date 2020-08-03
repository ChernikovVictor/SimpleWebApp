package application.service;

import application.dao.city.CityDAO;
import application.dto.city.CityDTO;
import application.dto.city.CityMapper;
import application.entity.City;
import application.exception.NoSuchElementException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CityService {

    @Inject
    private CityDAO cityDAO;

    public CityDTO findById(Long id) throws NoSuchElementException {
        City city = cityDAO.findById(id).orElseThrow(NoSuchElementException::new);
        return CityMapper.INSTANCE.cityToCityDto(city);
    }

    public List<CityDTO> findAll() {
        return CityMapper.INSTANCE.citiesToCityDTOs(cityDAO.findAll());
    }
}
