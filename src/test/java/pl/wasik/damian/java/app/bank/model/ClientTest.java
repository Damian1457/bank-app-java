package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class ClientTest {

    @Test
    void openAccount() {
        //Given
        Client client = new Client("Damian", "Wasik", new Address());

        //When
        Account account = client.openAccount();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(account, " account is null"),
                () -> Assertions.assertNotNull(account.getNumber(), " account number is null")
        );
    }

    @Test
    void testAllAccounts() {
        //Given
        Client client = new Client("Damian", "Wasik", new Address());
        Account accountDamian = client.openAccount();
        Account accountJacek = client.openAccount();

        //When
        List<Account> accounts = client.allAccounts();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, accounts.size(), " 2 is not equals account.size()"),
                () -> Assertions.assertEquals(accountDamian, accounts.get(0), " accountDamian is not equals accounts.get(0)"),
                () -> Assertions.assertEquals(accountJacek, accounts.get(1), " accountDamian is not equals accounts.get(1)")
        );

    }
}
