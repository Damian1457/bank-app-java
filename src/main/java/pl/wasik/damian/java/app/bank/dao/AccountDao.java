package pl.wasik.damian.java.app.bank.dao;

import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.exception.create.CreateAccountException;
import pl.wasik.damian.java.app.bank.exception.delete.DeleteAccountException;
import pl.wasik.damian.java.app.bank.exception.list.ListAccountException;
import pl.wasik.damian.java.app.bank.exception.read.ReadAccountException;
import pl.wasik.damian.java.app.bank.exception.update.UpdateAccountException;
import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.utils.UniqueIdGenerator;

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

    public void clearDatabaseRecords() {
        LOGGER.info("clearDatabaseRecords()");
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            Statement statement = connection.createStatement();
            boolean execute = statement.execute("DELETE FROM ACCOUNTS;");
            LOGGER.info("execute: " + execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("clearDatabaseRecords(...)");
    }

    //Przepis na korzystanie z JDBC
    //1. DriverManager
    //2. Connection
    //3. Statement/PrepareStatement
    //4. ResultSet
    //https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html

    // C - create
//    public void create(int id, String accountNumber, double balance) {
    public Account create(Account account) throws AccountException {
        LOGGER.info("create(" + account + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, UniqueIdGenerator.getNextId(connection, "ACCOUNTS_SEQ"));
            preparedStatement.setString(2, account.getNumber());
            preparedStatement.setDouble(3, account.balance());
            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("create(...) = " + executeUpdate);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new CreateAccountException("The account cannot be created", e);
        }
        return account;
    }

    // R - read
    public Account read(int id) throws AccountException { //Only AccountException
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
            throw new ReadAccountException("There is no account with this id number", e);
        }
        LOGGER.info("read(...) = " + null);
        return null;
    }

    // U - update
    public Account update(Account account) throws AccountException {
        LOGGER.info("update(" + account + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ACCOUNTS SET ACC_NO=?, BALANCE=? WHERE ID=?");
            preparedStatement.setString(1, account.getNumber());
            preparedStatement.setDouble(2, account.balance());
            preparedStatement.setInt(3, account.getId());

            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("update(...) = " + executeUpdate);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new UpdateAccountException("No account to change", e);
        }
        return account;
    }

    // D - delete
    public int delete(int id) throws AccountException {
        LOGGER.info("delete(" + id + ")");

        int executeDelete = 0;

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACCOUNTS WHERE ID=?");
            preparedStatement.setInt(1, id);
            executeDelete = preparedStatement.executeUpdate();
            LOGGER.info("delete(...) = " + executeDelete);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new DeleteAccountException("There is no account with this id number to delete", e);
        }
        return executeDelete;
    }

    // L - list
    public List<Account> list() throws AccountException {
        List<Account> accounts = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS ORDER BY ID;");
            ResultSet resultSet = preparedStatement.executeQuery();
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
            throw new ListAccountException("The list of accounts is empty", e);
        }
        return accounts;
    }
}
