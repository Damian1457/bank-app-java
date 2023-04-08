package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.create.CreateAccountException;
import pl.wasik.damian.java.app.bank.exception.delete.DeleteAccountException;
import pl.wasik.damian.java.app.bank.exception.list.ListAccountException;
import pl.wasik.damian.java.app.bank.exception.read.ReadAccountEcception;
import pl.wasik.damian.java.app.bank.exception.update.UpdateAccountException;
import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.utils.UniqueIdGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

class AccountDaoIntegrationTest {

    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    private static final double ACCOUNT_BALANCE_100 = 100.00;
    private static final double ACCOUNT_BALANCE_300 = 300.00;
    private static final int ACCOUNTS_SIZE_1 = 1;
    private static final int ACCOUNT_ID_1 = 1;

    @BeforeEach
    void setUp() throws SQLException {
        AccountDao accountDao = new AccountDao();
        accountDao.clearDatabaseRecords();

        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        UniqueIdGenerator uniqueIdGenerator = new UniqueIdGenerator();
        uniqueIdGenerator.clearAccountTable(connection);
    }

//    @AfterEach
//    void clearUp() {
//        AccountDao accountDao = new AccountDao();
//        accountDao.clearDatabaseRecords();
//    }

    @Test
    void givenCreateNewAccount_whenCreateAccountInDatabases_thenSearchAccountId1() throws CreateAccountException, ReadAccountEcception {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);
        Account jolaAccount = new Account("1356-2000-1789-8978", 200.00);

        //When
        accountDao.create(damianAccount);
        accountDao.create(jolaAccount);
        Account searchDamianAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACCOUNT_BALANCE_100, searchDamianAccount.balance(), " the account balance isn't equals " + ACCOUNT_BALANCE_100),
                () -> Assertions.assertNotNull(searchDamianAccount, " Damian account does not exist ")
        );
    }

    @Test
    void givenCreateNewAccount_whenReadAccountId1_thenCheckTheAccountNumber() throws CreateAccountException, ReadAccountEcception {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);

        //When
        accountDao.create(damianAccount);
        Account searchDamianAccount = accountDao.read(1);

        //Then
        Assertions.assertEquals("1355-2000-1789-8978", searchDamianAccount.getNumber(), " account numbers are not the same");
    }

    @Test
    void givenCreateNewAccount_whenUpdateAccount_thenCheckEqualsBalancesOfAccounts() throws CreateAccountException, ReadAccountEcception, UpdateAccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);
        Account updateDamianAccount = new Account(ACCOUNT_ID_1, "1234-1428-8967-5698", ACCOUNT_BALANCE_300);

        //When
        accountDao.create(damianAccount);
        accountDao.update(updateDamianAccount);
        Account searchDamianAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertNotEquals(searchDamianAccount.balance(), damianAccount.balance(), " balances are equal");
    }

    @Test
    void givenCreateNewAccount_whenDeleteAccountById_thenCheckingToSeeIfTheAccountHasBeenDeleted() throws CreateAccountException, ReadAccountEcception, DeleteAccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);

        //When
        accountDao.create(damianAccount);
        accountDao.delete(1);
        Account searchDamianAccount = accountDao.read(1);

        //Then
        Assertions.assertNull(searchDamianAccount, " damianAccount still exists");
    }

    @Test
    void givenCreateNewAccount_whenAddingTheNewAccountToTheList_theCheckingTheListSizeEquals1() throws CreateAccountException, ListAccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);

        //When
        accountDao.create(damianAccount);
        List<Account> accounts = accountDao.list();
        int listSize = accounts.size();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(accounts, " accounts is null"),
                () -> Assertions.assertEquals(ACCOUNTS_SIZE_1, listSize, " account size isn't equals" + ACCOUNTS_SIZE_1)
        );
    }
}
// TODO: 07.03.2023 Zamienić testy jednostkowe na integracyjne - dwie metody.
//  Zapewnić za pomocą dao metody do weryfinacji innych metod: read() musi zrobić najpierw create().

// TODO: 21.03.2023 Poprawić metodę create w AccountDao i testy jednostkowe.