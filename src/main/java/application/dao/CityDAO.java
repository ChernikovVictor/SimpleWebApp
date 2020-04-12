package application.dao;

import application.model.City;
import application.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityDAO {

    public Optional<City> findById(Long id) {

        String getCitySql = "SELECT * FROM cities WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getCitySql)) {

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String station  = resultSet.getString("station");
                return Optional.ofNullable(City.builder()
                        .id(id)
                        .name(name)
                        .station(station)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
