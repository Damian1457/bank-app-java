package pl.wasik.damian.java.app.bank.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniqueIdGenerator {

    private static final String ID_SEQUENCE = "ACCOUNTS_SEQqqq";
    private static final Logger LOGGER = Logger.getLogger(UniqueIdGenerator.class.getName());

    public static Integer getNextId(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT NEXTVAL('" + ID_SEQUENCE + "')");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return null;
    }

    public void clearAccountTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM ACCOUNTS");
            statement.executeUpdate("ALTER SEQUENCE ACCOUNTS_SEQ RESTART WITH 1");
            LOGGER.info("Account table cleared");
        }
    }

//    private static final Logger LOGGER = Logger.getLogger(UniqueIdGenerator.class.getName());
//    private static int idCounter = 0;
//
//    public static synchronized int generateId() {
//        // FIXME generowanie identyfikatorów po stronie bazy danych za pomocą sekwencji.
//        // Co to jest sekwencja w bazie danych?
//        // Jak użyć sekwencji w kodzie Java?
//
//        ++idCounter;
//        LOGGER.info("generateId() = " + idCounter);
//        return idCounter;
//    }
}
