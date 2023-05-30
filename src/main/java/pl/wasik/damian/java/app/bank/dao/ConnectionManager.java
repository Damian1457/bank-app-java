package pl.wasik.damian.java.app.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    // FIXME: 30.05.2023 dodać loggery do konstruktora i do metod.
    private static ConnectionManager connectionManager;
    private Connection connection;

    private ConnectionManager() {
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() {

//        try {
//            if (this.connection != null && this.connection.isClosed()) {
//                try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
//                    this.connection = connection;
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                return this.connection;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            if (this.connection != null && this.connection.isClosed()) {
                this.connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this.connection;
    }
}
