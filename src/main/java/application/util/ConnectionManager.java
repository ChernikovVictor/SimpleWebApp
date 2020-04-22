package application.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException, NamingException {

        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/myDataSource");
        return ds.getConnection();
    }

}
