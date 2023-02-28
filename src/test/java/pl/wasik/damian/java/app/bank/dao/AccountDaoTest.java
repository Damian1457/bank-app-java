package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.model.Account;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {

    @Test
    void list() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        accountDao.list();

        //Then

    }

    @Test
    void create() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(9, "123456789", 700.00);

        //When
        accountDao.create(account);

        //Then


    }
}