package application.dao.city;

import application.dao.GenericDAOImpl;
import application.entity.City;

public class CityDAOImpl extends GenericDAOImpl<City, Long> implements CityDAO {

    public CityDAOImpl() {
        super(City.class);
    }

}
