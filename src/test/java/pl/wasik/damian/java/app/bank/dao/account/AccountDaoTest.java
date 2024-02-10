package pl.wasik.damian.java.app.bank.dao.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.dao.AccountDao;
import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.utils.UniqueIdGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    @BeforeEach
    void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        UniqueIdGenerator uniqueIdGenerator = new UniqueIdGenerator();
        uniqueIdGenerator.clearTable(connection, "ACCOUNTS", "ACCOUNTS_SEQ");
    }

    @Test
    void create() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damian = new Account("1356-2000-1789-8978", 100.00);

        //When
        Account damianAccount = accountDao.create(damian);

        //Then
        Assertions.assertNotNull(damianAccount, "Account is null");
    }

    @Test
    void read() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damian = new Account("1356-2000-1789-8978", 100.00);

        //When
        accountDao.create(damian);
        Optional<Account> read = accountDao.read(1);

        //Then
        Assertions.assertEquals(100.00, read.orElseThrow().balance(), "Accounts balances isn't equals");
    }

    @Test
    void update() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damian = new Account("1356-2000-1789-8978", 100.00);
        Account jola = new Account("1434-1566-6677-4545", 200.00);

        //When
        accountDao.create(damian);
        Optional<Account> update = accountDao.update(jola);
        System.out.println(update);

        //Then
//        Assertions.assertNotEquals(200.00, update.orElseThrow().balance(), "Accounts balances are equals");
    }

    @Test
    void delete() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damian = new Account("1356-2000-1789-8978", 100.00);

        //When
        accountDao.create(damian);
        accountDao.delete(1);
        List<Account> accountList = accountDao.list();

        //Then
        Assertions.assertEquals(0, accountList.size(), "Account list size isn't equal 0");
    }

    @Test
    void list() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damian = new Account("1356-2000-1789-8978", 100.00);

        //When
        accountDao.create(damian);
        List<Account> accountList = accountDao.list();

        //Then
        Assertions.assertNotNull(accountList, "Account list is null");
    }
}