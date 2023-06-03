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
//            AccountDao accountDao = new AccountDao();
//            accountDao.clearDatabaseRecords();

        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        UniqueIdGenerator uniqueIdGenerator = new UniqueIdGenerator();
        uniqueIdGenerator.clearTable(connection, "ACCOUNTS", "ACCOUNTS_SEQ");
    }

//    @AfterEach
//    void clearUp() {
//        AccountDao accountDao = new AccountDao();
//        accountDao.clearDatabaseRecords();
//    }

    @Test
    void givenCreateNewAccount_whenCreateAccountInDatabases_thenSearchAccountId1() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);
        Account jolaAccount = new Account("1356-2000-1789-8978", -100.00);

        //When
        accountDao.create(damianAccount);
        accountDao.create(jolaAccount);
        Optional<Account> searchDamianAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACCOUNT_BALANCE_100, searchDamianAccount.orElseThrow().balance(), " the account balance isn't equals " + ACCOUNT_BALANCE_100),
                () -> Assertions.assertNotNull(searchDamianAccount, " Damian account does not exist ")
        );
    }

    @Test
    void givenCreateNewAccount_whenReadAccountId1_thenCheckTheAccountNumber() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);

        //When
        accountDao.create(damianAccount);
        Optional<Account> searchDamianAccount = accountDao.read(1);

        //Then
        Assertions.assertEquals("1355-2000-1789-8978", searchDamianAccount.orElseThrow().getNumber(), " account numbers are not the same");
    }

    @Test
    void givenCreateNewAccount_whenUpdateAccount_thenCheckEqualsBalancesOfAccounts() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);
        Account updateDamianAccount = new Account(ACCOUNT_ID_1, "1234-1428-8967-5698", ACCOUNT_BALANCE_300);

        //When
        accountDao.create(damianAccount);
        accountDao.update(updateDamianAccount);
        Optional<Account> searchDamianAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertNotEquals(searchDamianAccount.orElseThrow().balance(), damianAccount.balance(), " balances are equal");
    }

    @Test
    void givenCreateNewAccount_whenDeleteAccountById_thenCheckingToSeeIfTheAccountHasBeenDeleted() throws AccountException {
        //Given
        AccountDao accountDao = new AccountDao();
        Account damianAccount = new Account("1355-2000-1789-8978", ACCOUNT_BALANCE_100);

        //When
        accountDao.create(damianAccount);
        accountDao.delete(1);
        Optional<Account> searchDamianAccount = accountDao.read(1);

        //Then
        Assertions.assertTrue(searchDamianAccount.isEmpty(), " damianAccount still exists");
    }

    @Test
    void givenCreateNewAccount_whenAddingTheNewAccountToTheList_thenCheckingTheListSizeEqualsOne() throws AccountException {
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

    @Test
    void testAccountException() throws AccountException {
        //Given
        Account account = new Account("21000122222", -100);
        AccountDao accountDao = new AccountDao();

        //When
        //Then
        Assertions.assertDoesNotThrow(() -> accountDao.create(account), "exception was thrown");
    }
}
// TODO: 07.03.2023 Zamienić testy jednostkowe na integracyjne - dwie metody.
//  Zapewnić za pomocą dao metody do weryfinacji innych metod: read() musi zrobić najpierw create().

// TODO: 21.03.2023 Poprawić metodę create w AccountDao i testy jednostkowe.