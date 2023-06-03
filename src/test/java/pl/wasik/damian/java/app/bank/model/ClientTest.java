package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.AccountException;

import java.util.List;


class ClientTest {

    @Test
    void givenClient_whenOpenAccount_thenAccountIsNotNullAndHasNumber() {
        //Given
        Client client = new Client("Damian", "Wasik", new Address());

        //When
        Account account = client.openAccount();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(account, "Account is null"),
                () -> Assertions.assertNotNull(account.getNumber(), "Account number is null")
        );
    }

    @Test
    void givenClientWithTwoAccounts_whenRetrieveAllAccounts_thenReturnAccountList() {
        //Given
        Client client = new Client("Damian", "Wasik", new Address());
        Account accountDamian = client.openAccount();
        Account accountJacek = client.openAccount();

        //When
        List<Account> accounts = client.allAccounts();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, accounts.size(), " 2 is not equals account.size()"),
                () -> Assertions.assertEquals(accountDamian, accounts.get(0), "AccountDamian is not equals accounts.get(0)"),
                () -> Assertions.assertEquals(accountJacek, accounts.get(1), "AccountDamian is not equals accounts.get(1)")
        );

    }
}
