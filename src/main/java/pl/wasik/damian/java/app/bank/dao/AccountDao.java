package pl.wasik.damian.java.app.bank.dao;

import pl.wasik.damian.java.app.bank.constants.account.AccountDatabaseConstants;
import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.exception.account.CreateAccountException;
import pl.wasik.damian.java.app.bank.exception.account.DeleteAccountException;
import pl.wasik.damian.java.app.bank.exception.account.ListAccountException;
import pl.wasik.damian.java.app.bank.exception.account.ReadAccountException;
import pl.wasik.damian.java.app.bank.exception.account.UpdateAccountException;
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
import java.util.Optional;
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

    public Account create(Account account) throws AccountException {
        LOGGER.info("create(" + account + ")");

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountDatabaseConstants.CREATE_ACCOUNT)) {
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

    public Optional<Account> read(int id) throws AccountException {
        LOGGER.info("read(" + id + ")");

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountDatabaseConstants.READ_ACCOUNT)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int accountId = resultSet.getInt(AccountDatabaseConstants.COLUMN_ID);
                String accountNumber = resultSet.getString(AccountDatabaseConstants.COLUMN_ACC_NO);
                double accountBalance = resultSet.getDouble(AccountDatabaseConstants.COLUMN_BALANCE);
                Account account = new Account(accountId, accountNumber, accountBalance);
                LOGGER.info("read(...) = " + account);
                return Optional.of(account);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ReadAccountException("There is no account with this id number", e);
        }

        LOGGER.info("read(...) = " + id);
        return Optional.empty();
    }

    public Optional<Account> update(Account account) throws AccountException {
        LOGGER.info("update(" + account + ")");

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountDatabaseConstants.UPDATE_ACCOUNT)) {
            preparedStatement.setString(1, account.getNumber());
            preparedStatement.setDouble(2, account.balance());
            preparedStatement.setInt(3, account.getId());

            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                return Optional.of(account);
            }
            LOGGER.info("update(...) = " + executeUpdate);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new UpdateAccountException("No account to change", e);
        }
        
        return Optional.empty();
    }

    public void delete(int id) throws AccountException {
        LOGGER.info("delete(" + id + ")");

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountDatabaseConstants.DELETE_ACCOUNT)) {
            preparedStatement.setInt(1, id);
            int executeDelete = preparedStatement.executeUpdate();
            LOGGER.info("delete(...) = " + executeDelete);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new DeleteAccountException("There is no account with this id number to delete", e);
        }
    }

    public List<Account> list() throws AccountException {
        LOGGER.info("list()");
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountDatabaseConstants.GET_ACCOUNTS_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            LOGGER.info("" + resultSet);

            while (resultSet.next()) {
                int id = resultSet.getInt(AccountDatabaseConstants.COLUMN_ID);
                String number = resultSet.getString(AccountDatabaseConstants.COLUMN_ACC_NO);
                double balance = resultSet.getDouble(AccountDatabaseConstants.COLUMN_BALANCE);
                Account account = new Account(id, number, balance);
                accounts.add(account);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ListAccountException("The list of accounts is empty", e);
        }

        LOGGER.info("list(...) = " + accounts);
        return accounts;
    }
}
