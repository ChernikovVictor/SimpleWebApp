package application.service;

import lombok.extern.slf4j.Slf4j;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class ConnectionManager {

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            return new Configuration().configure().buildSessionFactory();
//        }
//        catch (Throwable ex) {
//            log.error("Initial SessionFactory creation failed", ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    /* hibernate */
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }

    /* JDBC + JNDI */
    public static Connection getConnection() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/myDataSource");
        return ds.getConnection();
    }
}
