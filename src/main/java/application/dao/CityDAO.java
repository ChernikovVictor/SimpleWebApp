package application.dao;

import application.exception.NoSuchElementException;
import application.model.City;
import application.util.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
