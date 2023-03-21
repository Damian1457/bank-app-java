package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.utils.UniqueIdGenerator;

import java.util.List;

class AccountDaoIntegrationTest {

    private static final String ACCOUNT_NUMBER = "1456-4350-5620-3598";
    private static final double ACCOUNT_BALANCE_100 = 100.00;
    private static final int LIST_SIZE_0 = 0;
    private static final int ACCOUNT_ID_1 = 1;
    private static final int ACCOUNT_LIST_SIZE_1 = 1;
    private static final int GENERATE_UNIQUE_ID = UniqueIdGenerator.generateId();

    @BeforeEach
    void setUp() {
        AccountDao accountDao = new AccountDao();
        accountDao.clearDatabaseRecords();
    }

//    @AfterEach
//    void clearUp() {
//        AccountDao accountDao = new AccountDao();
//        accountDao.clearDatabaseRecords();
//    }

    @Test
    void givenCreateNewAccount_whenCallingTheList_thenCheckingTheList() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        accountDao.create(new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_100));

        List<Account> accounts = accountDao.list();
        int accountsSize = accounts.size();

        Account readAccount = accountDao.read(ACCOUNT_ID_1);
        double accountBalance_100 = readAccount.balance();
        double expectedBalance_100 = accounts.get(0).balance();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(accounts, " accounts is null"),
                () -> Assertions.assertEquals(ACCOUNT_LIST_SIZE_1, accountsSize, ACCOUNT_LIST_SIZE_1 + " isn't equals accountsSize"),
                () -> Assertions.assertEquals(expectedBalance_100, accountBalance_100, " expectedBalance isn't equals accountBalance")
        );
    }

    @Test
    void givenCreateNewAccount_whenReadTheAccount_thenCheckingAccountId() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        accountDao.create(new Account(GENERATE_UNIQUE_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE_100));

        Account readAccount = accountDao.read(ACCOUNT_ID_1);
        int expectedId_1 = readAccount.getId();
        double actualAccountBalance_100 = readAccount.balance();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACCOUNT_BALANCE_100, actualAccountBalance_100, ACCOUNT_BALANCE_100 + " isn't equals actual balance"),
                () -> Assertions.assertEquals(ACCOUNT_ID_1, expectedId_1, ACCOUNT_ID_1 + " isn't equals expected id"),
                () -> Assertions.assertNotNull(readAccount, "read account should not be null")
        );

    }

    @Test
    void givenCreateNewAccount_whenUpdateTheAccount_thenCheckingTheUpdatedAccount() {
        // Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(GENERATE_UNIQUE_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE_100);
        Account updateAccount = new Account(ACCOUNT_ID_1, "1234-4789-9090-8745", 200.00);

        // When
        accountDao.create(account);
        Account updatedAccount = accountDao.update(updateAccount);
        Account readAccount = accountDao.read(ACCOUNT_ID_1);

        // Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(updateAccount.getNumber(), updatedAccount.getNumber(), "account number not updated"),
                () -> Assertions.assertEquals(updateAccount.balance(), updatedAccount.balance(), "account balance not updated"),
                () -> Assertions.assertEquals(updateAccount.getId(), updatedAccount.getId(), "account ID not updated"),
                () -> Assertions.assertEquals(updateAccount.getNumber(), readAccount.getNumber(), "incorrect account number retrieved"),
                () -> Assertions.assertEquals(updateAccount.balance(), readAccount.balance(), "incorrect account balance retrieved"),
                () -> Assertions.assertEquals(updateAccount.getId(), readAccount.getId(), "incorrect account ID retrieved")
        );
    }

    @Test
    void givenCreateNewAccount_whenCreateTheAccountAndDelete_thenCheckingTheList() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(GENERATE_UNIQUE_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE_100);

        //When
        accountDao.create(account);
        accountDao.delete(ACCOUNT_ID_1);
        List<Account> accounts = accountDao.list();
        int actualListSize_0 = accounts.size();

        //Then
//        Assertions.assertEquals(0, account.balance(), "account balance is not zero after deletion");
        Assertions.assertEquals(LIST_SIZE_0, actualListSize_0, LIST_SIZE_0 + " isn't equals actualListSize_0");
        Assertions.assertFalse(accounts.contains(account), "deleted account still present in the list");
//        Assertions.assertNull(account.getId(), "account ID is not null after deletion");
    }

    @Test
    void givenCreateNewAccount_whenCallingListMethod_thenCheckingListSize() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account1 = new Account(GENERATE_UNIQUE_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE_100);

        //When
        accountDao.create(account1);
        List<Account> accounts = accountDao.list();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACCOUNT_LIST_SIZE_1, accounts.size(), " accounts SIZE is not equals " + ACCOUNT_LIST_SIZE_1),
                () -> Assertions.assertNotNull(accounts, " accounts is null")
        );
    }
}
// TODO: 07.03.2023 Zamienić testy jednostkowe na integracyjne - dwie metody.
//  Zapewnić za pomocą dao metody do weryfinacji innych metod: read() musi zrobić najpierw create().

// TODO: 21.03.2023 Poprawić metodę create w AccountDao i testy jednostkowe.