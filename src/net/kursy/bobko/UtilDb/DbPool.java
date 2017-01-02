package net.kursy.bobko.UtilDb;


import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbPool {

    private static final Logger logger = Logger.getLogger(DbPool.class);
    private static DbPool instance;
    private DataSource ds;

    public static DbPool getInstance() {
        if (instance == null) {
            instance = new DbPool();
        }
        return instance;
    }

    private DbPool() {
        logger.info("Making Data Source");
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");

            ds = (DataSource) envContext.lookup("jdbc/mydb");
            logger.info("The Data Source: " + ds);

        } catch (NamingException e) {
            logger.error("Can't name the Data Source: " + e);
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            logger.error("Connection failed " + e);
            e.printStackTrace();
        }
        return connection;
    }

    public static void realese(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Unable to close connection " + e);
                e.printStackTrace();
            }
        }
    }
}
