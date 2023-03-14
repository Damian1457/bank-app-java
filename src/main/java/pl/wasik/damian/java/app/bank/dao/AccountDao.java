package pl.wasik.damian.java.app.bank.dao;

import pl.wasik.damian.java.app.bank.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDao {
    private static final Logger LOGGER = Logger.getLogger(AccountDao.class.getName());

    //Przepis na korzystanie z JDBC
    //1. DriverManager
    //2. Connection
    //3. Statement/PrepareStatement
    //4. ResultSet
    //https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html

    // C - create
//    public void create(int id, String accountNumber, double balance) {
    public Account create(Account account) {

        LOGGER.info("create(" + account + ")");
        //INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(1, '12121212', 20.0);

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);
//            Statement statement = connection.createStatement();
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(10, '101111111111', 50.0);");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getNumber());
            preparedStatement.setDouble(3, account.balance());

            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("create(...) = " + executeUpdate);

        } catch (SQLException e) {
//            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return account;
    }

    // R - read
    public Account read(int id) {

        LOGGER.info("read(" + id + ")");

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ID=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int accountId = resultSet.getInt("ID");
                String accountNumber = resultSet.getString("ACC_NO");
                double accountBalance = resultSet.getDouble("BALANCE");
                Account account = new Account(accountId, accountNumber, accountBalance);
                LOGGER.info("read(...) = " + account);
                return account;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return null;
    }

    // U - update
    public Account update(Account account) {

        LOGGER.info("update(" + account + ")");

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ACCOUNTS SET ACC_NO=?, BALANCE=? WHERE ID=?");
            preparedStatement.setString(1, account.getNumber());
            preparedStatement.setDouble(2, account.balance());
            preparedStatement.setInt(3, account.getId());

            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("update(...) = " + executeUpdate);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return account;
    }

    // D - delete
    public void delete(int id) {

        LOGGER.info("delete(" + id + ")");

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACCOUNTS WHERE ID=?");
            preparedStatement.setInt(1, id);

            int executeDelete = preparedStatement.executeUpdate();
            LOGGER.info("delete(...) = " + executeDelete);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
    }

    // L - list
    public List<Account> list() {
        List<Account> accounts = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNTS ORDER BY ID;");
            LOGGER.info("" + resultSet);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String number = resultSet.getString("ACC_NO");
                double balance = resultSet.getDouble("BALANCE");
                Account account = new Account(id, number, balance);
                accounts.add(account);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return accounts;
    }
}
