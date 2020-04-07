package application.dao;

import application.exception.InsertionFailedException;
import application.exception.NoSuchElementException;
import application.model.Route;
import application.model.TransportKinds;
import application.util.ConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RouteDAO {

    public Route findById(Long id) throws NoSuchElementException {

        String getRouteSql = "SELECT * FROM routes WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getRouteSql)) {

            if (resultSet.next()) {
                Long departure_id = resultSet.getLong("departure_id");
                Long destination_id = resultSet.getLong("destination_id");
                String departure_time = resultSet.getString("departure_time");
                String arrival_time = resultSet.getString("arrival_time");
                Long transport_id = resultSet.getLong("transport_id");
                return Route.builder().id(id)
                        .departureId(departure_id)
                        .destinationId(destination_id)
                        .departureTime(departure_time)
                        .arrivalTime(arrival_time)
                        .transportId(transport_id).build();
            } else {
                throw new NoSuchElementException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoSuchElementException();
        }
    }

    public void add(Route route) throws InsertionFailedException {

        String addRouteSql = "INSERT INTO routes " +
                "(departure_id, destination_id, departure_time, arrival_time, transport_id) " +
                "VALUES (?, ?, ?, ? ,?);";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(addRouteSql)) {

            statement.setLong(1, route.getDepartureId());
            statement.setLong(2, route.getDestinationId());
            statement.setString(3, route.getDepartureTime());
            statement.setString(4, route.getArrivalTime());
            statement.setLong(5, route.getTransportId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InsertionFailedException();
        }
    }

    public void removeById(Long id) throws NoSuchElementException {

        String removeRouteSql = "DELETE FROM routes WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            int deleted = statement.executeUpdate(removeRouteSql);
            if (deleted == 0) {
                throw new NoSuchElementException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoSuchElementException();
        }
    }

    public void updateById(Long id, Route route) {

        String updateRouteSql = "UPDATE routes " +
                "SET departure_id = ?, destination_id = ?, departure_time = ?, arrival_time = ?, transport_id = ? " +
                "WHERE id = ?;";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateRouteSql)) {

            statement.setLong(1, route.getDepartureId());
            statement.setLong(2, route.getDestinationId());
            statement.setString(3, route.getDepartureTime());
            statement.setString(4, route.getArrivalTime());
            statement.setLong(5, route.getTransportId());
            statement.setLong(6, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Route> findAll() {
        return findAllBySqlCommand("SELECT * FROM routes;");
    }

    public List<Route> findAllByTransportKind(TransportKinds kind) {
        String sqlCommand = "SELECT routes.id, departure_id, destination_id, departure_time, arrival_time, transport_id " +
                "FROM routes, transports WHERE routes.transport_id = transports.id " +
                "AND transports.kind = \"" + kind.name() + "\";";
        return findAllBySqlCommand(sqlCommand);
    }

    private List<Route> findAllBySqlCommand(String sqlCommand) {

        LinkedList<Route> routes = new LinkedList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.prepareStatement(sqlCommand);
             ResultSet resultSet = statement.executeQuery(sqlCommand)) {

            while (resultSet.next()) {
                Route route = Route.builder().build();
                route.setId(resultSet.getLong("id"));
                route.setDepartureId(resultSet.getLong("departure_id"));
                route.setDestinationId(resultSet.getLong("destination_id"));
                route.setDepartureTime(resultSet.getString("departure_time"));
                route.setArrivalTime(resultSet.getString("arrival_time"));
                route.setTransportId(resultSet.getLong("transport_id"));

                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }
}
