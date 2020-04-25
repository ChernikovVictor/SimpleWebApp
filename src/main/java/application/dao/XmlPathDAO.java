package application.dao;

import application.exception.InsertionFailedException;
import application.model.XmlPath;
import application.util.ConnectionManager;

import javax.naming.NamingException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class XmlPathDAO {

    public Optional<XmlPath> findById(Long id) {

        String getXmlPathSql = "SELECT * FROM xml_paths WHERE id = " + id + ";";

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getXmlPathSql)) {

            if (resultSet.next()) {
                String path = resultSet.getString("path");
                return Optional.ofNullable(XmlPath.builder()
                        .id(id)
                        .path(path)
                        .build());
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void add(XmlPath xmlPath) throws InsertionFailedException {

        String addXmlPathSql = "INSERT INTO xml_paths (path) VALUES (?);";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(addXmlPathSql)) {

            statement.setString(1, xmlPath.getPath());
            statement.executeUpdate();

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new InsertionFailedException();
        }
    }

    public List<XmlPath> findAll() {

        String getAllRowsSql = "SELECT * FROM xml_paths;";

        LinkedList<XmlPath> xmlPaths = new LinkedList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllRowsSql)) {

            while (resultSet.next()) {
                XmlPath xmlPath = XmlPath.builder()
                        .id(resultSet.getLong("id"))
                        .path(resultSet.getString("path"))
                        .build();

                xmlPaths.add(xmlPath);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        return xmlPaths;
    }
}
