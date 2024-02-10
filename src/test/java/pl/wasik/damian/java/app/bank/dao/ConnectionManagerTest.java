package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class ConnectionManagerTest {

    @Test
    void getConnection() {
        //Given

        //When
        Connection connection = ConnectionManager.getInstance().getConnection();

        //Then
        Assertions.assertNotNull(connection, "Connection is equal null");

    }
}