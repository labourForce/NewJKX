package net.kursy.bobko.UtilDb;


import net.kursy.bobko.managercommands.ManagerDb;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DbPoolConnection {

    private static final Logger logger = Logger.getLogger(DbPoolConnection.class);

    private static final DbPoolConnection INSTANCE = new DbPoolConnection();
    private static BlockingQueue<Connection> connections;
    private final static int SIZE_DB_POOL = 20;
    private final String DRIVER = "DB_DRIVER";


    private  DbPoolConnection() {
        connections =  new ArrayBlockingQueue<>(SIZE_DB_POOL);

    }

    public static DbPoolConnection getInstance() {
        return INSTANCE;
    }


    private Connection createConnection() {
        try {

            return DriverManager.getConnection(
                    ManagerDb.getInstance().getProperty("URL"),
                    ManagerDb.getInstance().getProperty("LOGIN"),
                    ManagerDb.getInstance().getProperty("PASS"));


        } catch (SQLException exception) {
            logger.error("Error DBPool createConnection", exception);
        }
        return null;
    }

    private void preparationPool() {
        try {
            Class.forName(ManagerDb.getInstance().getProperty(DRIVER));
        } catch (ClassNotFoundException exception) {
            logger.error("Error DBPool preparationPool", exception);

        }
        try {
            for (int i = 0; i < connections.remainingCapacity() - 5; i++) {
                connections.put(createConnection());
            }

        } catch (InterruptedException exception) {
            logger.error("ERROR DBPoll preparationPoll", exception);
        }
    }

    public Connection getConnection() {
        preparationPool();
        try {
            Connection c = connections.take();
            if(c == null){
                connections.put(createConnection());
                c = connections.take();
            }
            return c;
        } catch (InterruptedException exception) {
            logger.error("Error DBPool getConnection", exception);
        }
        return getConnection();
    }

    public void release(Connection connection) throws SQLException {
        try {
            if (connection != null) {
                connections.put(connection);
            } else {
                connections.put(createConnection());
            }
        } catch (InterruptedException exception) {
            logger.error("ERROR DBPool release", exception);
        }
    }
}

