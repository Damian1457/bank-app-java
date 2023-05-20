package pl.wasik.damian.java.app.bank.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class UniqueIdGeneratorTest {

    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    @BeforeAll
    static void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @AfterAll
    static void tearDown() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.close();
    }

    @Test
    void generateId() throws SQLException {
        //Given
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        //When
        int id1 = UniqueIdGenerator.getNextId(connection, "ACCOUNTS_SEQ");
        int id2 = UniqueIdGenerator.getNextId(connection, "ACCOUNTS_SEQ");

        //Then
        Assertions.assertNotEquals(id1, id2, " id1 is equals id2");
    }
}