package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.model.Account;

import java.util.List;

class AccountDaoIntegrationTest {

    private static final String ACCOUNT_NUMBER = "1456-4350-5620-3598";
    private static final double ACCOUNT_BALANCE = 100.00;
    private static final int ACCOUNT_ID_1 = 3;
    private static final int ACCOUNT_LIST_SIZE_1 = 1;

    @BeforeEach
    void setUp() {
        AccountDao accountDao = new AccountDao();
        accountDao.clearDatabaseRecords();
    }

    @Test
    void list() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(ACCOUNT_ID_1, ACCOUNT_NUMBER, ACCOUNT_BALANCE);

        //When
        accountDao.create(account);
        List<Account> accounts = accountDao.list();

        Account readAccount = accountDao.read(ACCOUNT_ID_1);
        String accountNumber = readAccount.getNumber();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACCOUNT_LIST_SIZE_1, accounts.size(), " accounts SIZE is not equals " + ACCOUNT_LIST_SIZE_1),
                () -> Assertions.assertEquals(accountNumber, ACCOUNT_NUMBER, " accountNumber isn't equals " + ACCOUNT_NUMBER)
        );
    }

    @Test
    void create() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(ACCOUNT_ID_1, ACCOUNT_NUMBER, ACCOUNT_BALANCE);

        //When
        accountDao.create(account);
        Account readAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertAll(

                () -> Assertions.assertEquals(readAccount.balance(), ACCOUNT_BALANCE, " readAccount.balance() isn't equals " + ACCOUNT_BALANCE)
        );
    }

    @Test
    void read() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(ACCOUNT_ID_1, ACCOUNT_NUMBER, ACCOUNT_BALANCE);

        //When
        accountDao.create(account);
        Account readAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(readAccount, " readAccount is null"),
                () -> Assertions.assertEquals(readAccount.balance(), ACCOUNT_BALANCE, " readAccount.balance() isn't equals " + ACCOUNT_BALANCE + "")
        );

    }

    @Test
    void update() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(ACCOUNT_ID_1, ACCOUNT_NUMBER, ACCOUNT_BALANCE);
        Account updateAccount = new Account(ACCOUNT_ID_1, "1234-4789-9090-8745", 300.00);

        //When
        accountDao.create(account);
        Account update = accountDao.update(updateAccount);
        Account readAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(update.getId(), readAccount.getId(), " update.getId() isn't equals "),
                () -> Assertions.assertEquals(update.getNumber(), readAccount.getNumber(), " update.getNumber isn't equals "),
                () -> Assertions.assertEquals(update.balance(), readAccount.balance(), " update.balance() isn't equals ")
        );
    }

    @Test
    void delete() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(ACCOUNT_ID_1, ACCOUNT_NUMBER, ACCOUNT_BALANCE);

        //When
        accountDao.create(account);
        accountDao.delete(ACCOUNT_ID_1);
        Account deletedAccount = accountDao.read(ACCOUNT_ID_1);

        //Then
        Assertions.assertNull(deletedAccount, " deletedAccount isn't null");
    }
}
// TODO: 07.03.2023 Zamienić testy jednostkowe na integracyjne - dwie metody.
//  Zapewnić za pomocą dao metody do weryfinacji innych metod: read() musi zrobić najpierw create().