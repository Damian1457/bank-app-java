package pl.wasik.damian.java.app.bank.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniqueIdGenerator {
    private static final Logger LOGGER = Logger.getLogger(UniqueIdGenerator.class.getName());

    public static Integer getNextId(Connection connection, String sequenceName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT NEXTVAL('" + sequenceName + "')");
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return 0;
    }

    public void clearTable(Connection connection, String tableName, String sequenceName) throws SQLException {
        LOGGER.info("clearTable(" + connection + ", " + tableName + ", " + sequenceName + ")");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + tableName);
            statement.executeUpdate("ALTER SEQUENCE " + sequenceName + " RESTART WITH 1");
        }
        LOGGER.info("clearTable(...)");
    }
}
