package pl.wasik.damian.java.app.bank.dao;

import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.model.Account;

import java.util.List;

class AccountDaoTest {

    @Test
    void list() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        List<Account> account = accountDao.list();

        for (Account accounts : account) {
            System.out.println(accounts);
        }

        //Then

    }

    @Test
    void create() {
        //Given
        AccountDao accountDao = new AccountDao();
        Account account = new Account(10, "122222222229", 800.00);

        //When
        accountDao.create(account);

        //Then


    }

    @Test
    void read() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        accountDao.read(10);

        //Then

    }

    @Test
    void update() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        accountDao.update(new Account(10, "122222222229", 900.00));

        //Then

    }

    @Test
    void delete() {
        //Given
        AccountDao accountDao = new AccountDao();

        //When
        accountDao.delete(10);

        //Then

    }
}
// TODO: 07.03.2023 Zamienić testy jednostkowe na integracyjne - dwie metody.
//  Zapewnić za pomocą dao metody do weryfinacji innych metod: read() musi zrobić najpierw create().