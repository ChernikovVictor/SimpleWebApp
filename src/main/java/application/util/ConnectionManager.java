package application.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static final ComboPooledDataSource DATA_SOURCE = new ComboPooledDataSource();

    static {

        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src\\main\\resources\\database.properties"))) {
            properties.load(in);
            DATA_SOURCE.setDriverClass(properties.getProperty("driverClassName"));
            DATA_SOURCE.setJdbcUrl(properties.getProperty("url"));
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

        properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src\\main\\resources\\datasource.properties"))) {
            properties.load(in);
            DATA_SOURCE.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DATA_SOURCE.setUser(properties.getProperty("user"));
        DATA_SOURCE.setPassword(properties.getProperty("password"));
        DATA_SOURCE.setMaxStatements(180);
        DATA_SOURCE.setMaxStatementsPerConnection(180);
        DATA_SOURCE.setMinPoolSize(50);
        DATA_SOURCE.setAcquireIncrement(10);
        DATA_SOURCE.setMaxPoolSize(60);
        DATA_SOURCE.setMaxIdleTime(30);
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
