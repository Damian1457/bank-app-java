package pl.wasik.damian.java.app.bank.dao;

import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.model.Address;
import pl.wasik.damian.java.app.bank.utils.UniqueIdGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDao {
    private static final Logger LOGGER = Logger.getLogger(AddressDao.class.getName());

    public Address create(Address address) throws AccountException {
        LOGGER.info("create(" + address + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            LOGGER.info("" + connection);

            Integer id = UniqueIdGenerator.getNextId(connection, "ADDRESS_SEQ");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ADDRESS (ID, STREET, HOUSE_NUMBER, POSTAL_CODE) VALUES(?, ?, ?, ?);");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getHouseNumber());
            preparedStatement.setString(4, address.getPostalCode());
            int executeUpdate = preparedStatement.executeUpdate();

            address.setId(id);

            LOGGER.info("create(...) = " + executeUpdate);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new AccountException("The address cannot be created", e);
        }
        return address;
    }

    public Address read(int id) throws AccountException {
        LOGGER.info("read(" + id + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            LOGGER.info("" + connection);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ADDRESS WHERE ID=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int addressId = resultSet.getInt("ID");
                String street = resultSet.getString("STREET");
                String houseNumber = resultSet.getString("HOUSE_NUMBER");
                String postalCode = resultSet.getString("POSTAL_CODE");
                Address address = new Address(addressId, street, houseNumber, postalCode);
                LOGGER.info("read(...) = " + address);
                return address;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new AccountException("There is no address with this id number", e);
        }
        LOGGER.info("read(...) = " + null);
        return null;
    }

    public Address update(Address address) throws AccountException {
        LOGGER.info("update(" + address + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ADDRESS SET STREET=?, HOUSE_NUMBER=?, POSTAL_CODE=? WHERE ID=?");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getHouseNumber());
            preparedStatement.setString(3, address.getPostalCode());
            preparedStatement.setInt(4, address.getId());

            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("update(...) = " + executeUpdate);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new AccountException("No address to change", e);
        }
        return address;
    }

    public int delete(int id) throws AccountException {
        LOGGER.info("delete(" + id + ")");
        int executeDelete = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            LOGGER.info("" + connection);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ADDRESS WHERE ID=?");
            preparedStatement.setInt(1, id);
            executeDelete = preparedStatement.executeUpdate();
            LOGGER.info("delete(...) = " + executeDelete);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new AccountException("There is no address with this id number to delete", e);
        }
        return executeDelete;
    }

    public List<Address> list() throws AccountException {
        LOGGER.info("list()");
        List<Address> addresses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, STREET, HOUSE_NUMBER, POSTAL_CODE FROM ADDRESS ORDER BY ID;");
            ResultSet resultSet = preparedStatement.executeQuery();
            LOGGER.info("" + resultSet);

            while (resultSet.next()) {
                int addressId = resultSet.getInt("ID");
                String street = resultSet.getString("STREET");
                String houseNumber = resultSet.getString("HOUSE_NUMBER");
                String postalCode = resultSet.getString("POSTAL_CODE");
                Address address = new Address(addressId, street, houseNumber, postalCode);
                addresses.add(address);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new AccountException("The list of addresses is empty", e);
        }
        LOGGER.info("list(...) = " + addresses);
        return addresses;
    }
}
// TODO: 23.05.2023 Singleton database connection.
// Odczytywanie parametrów połączenia(singleton database connection) z plików properties.
// Optional w metodzie read i update.
// TODO: 23.05.2023 Zrobić nową tabele users z kluczem obcym do tabeli clients - analogicznie do client i address.

