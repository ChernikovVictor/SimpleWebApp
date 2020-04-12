package application.dao;

import application.model.Transport;
import application.model.TransportKinds;
import application.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TransportDAO {

    public Optional<Transport> findById(Long id) {

        String getTransportSql = "SELECT * FROM transports WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getTransportSql)) {

            if (resultSet.next()) {
                TransportKinds kind = TransportKinds.valueOf(resultSet.getString("kind").toUpperCase());
                String name = resultSet.getString("name");
                Long capacity = resultSet.getLong("capacity");
                return Optional.ofNullable(Transport.builder()
                        .id(id)
                        .kind(kind)
                        .name(name)
                        .capacity(capacity)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transports;
    }
}
