package application.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static String url;
    private static String username;
    private static String password;

    public static Connection getConnection() throws SQLException, IOException {

        if (url == null) {
            Properties properties = new Properties();
            try (InputStream in = Files.newInputStream(Paths.get("src\\main\\resources\\database.properties"))) {
                properties.load(in);
            }

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        }

        return DriverManager.getConnection(url, username, password);
    }

}
