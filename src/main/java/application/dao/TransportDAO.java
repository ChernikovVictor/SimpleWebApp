package application.dao;

import application.exception.NoSuchElementException;
import application.model.Transport;
import application.model.TransportKinds;
import application.util.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class TransportDAO {

    public Transport findById(Long id) throws NoSuchElementException {

        String getTransportSql = "SELECT * FROM transports WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getTransportSql)) {

            if (resultSet.next()) {
                TransportKinds kind = TransportKinds.valueOf(resultSet.getString("kind").toUpperCase());
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

    public List<Transport> findAll() {

        String getAllRowsSql = "SELECT * FROM transports;";

        LinkedList<Transport> transports = new LinkedList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.prepareStatement(getAllRowsSql);
             ResultSet resultSet = statement.executeQuery(getAllRowsSql)) {

            while (resultSet.next()) {
                Transport transport = Transport.builder().build();
                transport.setId(resultSet.getLong("id"));
                transport.setKind(TransportKinds.valueOf(resultSet.getString("kind").toUpperCase()));
                transport.setName(resultSet.getString("name"));
                transport.setCapacity(resultSet.getLong("capacity"));

                transports.add(transport);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return transports;
    }
}
