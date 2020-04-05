package application.dao;

import application.exception.NoSuchElementException;
import application.model.Transport;
import application.model.TransportKinds;
import application.util.ConnectionManager;

import java.io.IOException;
import java.sql.*;

public class TransportDAO {

    public Transport findById(Long id) throws NoSuchElementException {

        String getTransportSql = "SELECT * FROM transports WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getTransportSql)) {

            if (resultSet.next()) {
                TransportKinds kind = TransportKinds.valueOf(resultSet.getString("location").toUpperCase());
                String name = resultSet.getString("name");
                Long capacity = resultSet.getLong("capacity");
                return Transport.builder().id(id).kind(kind).name(name).capacity(capacity).build();
            } else {
                throw new NoSuchElementException();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new NoSuchElementException();
        }

    }
}
