package pl.wasik.damian.java.app.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private static final String DATABASE_URL = "jdbc:h2:~/test";
    private static ConnectionManager connectionManager;
    private Connection connection;

    private ConnectionManager() {
        LOGGER.info("ConnectionManager()");
        try {
            this.connection = DriverManager.getConnection(
                    DatabasePropertiesManager.getInstance().getValue("db.url"),
                    DatabasePropertiesManager.getInstance().getValue("db.username"),
                    DatabasePropertiesManager.getInstance().getValue("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("ConnectionManager(...)");
    }

    public static ConnectionManager getInstance() {
        LOGGER.info("getInstance()");
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        LOGGER.info("getInstance(...)");
        return connectionManager;
    }

    public Connection getConnection() {
        LOGGER.info("getConnection()");
        try {
            if (this.connection != null && this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(DATABASE_URL, "sa", "sa");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("getConnection(...)");
        return this.connection;
    }
}
