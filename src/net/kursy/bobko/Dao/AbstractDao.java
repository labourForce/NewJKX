package net.kursy.bobko.Dao;

import net.kursy.bobko.UtilDb.DbPoolConnection;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;

public class AbstractDao {

    private static final Logger logger = Logger.getLogger(AbstractDao.class);

    private final DbPoolConnection instance = DbPoolConnection.getInstance();

    public Connection getConnection() {
        return instance.getConnection();
    }

    public void closeConnection(Connection connection) {
        try {
            instance.release(connection);
        } catch (SQLException exception) {
            logger.error("AbstractDAO closeConn", exception);
        }
    }
}
