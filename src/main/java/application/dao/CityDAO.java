package application.dao;

import application.exception.NoSuchElementException;
import application.model.City;
import application.util.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CityDAO {

    public City findById(Long id) throws NoSuchElementException {

        String getCitySql = "SELECT * FROM cities WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getCitySql)) {

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String station  = resultSet.getString("station");
                return City.builder().id(id).name(name).station(station).build();
            } else {
                throw new NoSuchElementException();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new NoSuchElementException();
        }
    }

    public List<City> findAll() {

        String getAllRowsSql = "SELECT * FROM cities;";

        LinkedList<City> cities = new LinkedList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.prepareStatement(getAllRowsSql);
             ResultSet resultSet = statement.executeQuery(getAllRowsSql)) {

            while (resultSet.next()) {
                City city = City.builder().build();
                city.setId(resultSet.getLong("id"));
                city.setName(resultSet.getString("name"));
                city.setStation(resultSet.getString("station"));

                cities.add(city);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
