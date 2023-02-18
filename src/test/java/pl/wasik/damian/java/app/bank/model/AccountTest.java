package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.utils.UniqueIdentifierGenerator;

class AccountTest {
    public static final int ACCOUNT_BALANCE_0 = 0; //Stałej
    public static final int ACCOUNT_BALANCE_100 = 100; //Stałej

    @Test
    void givenAccountWithBalanceZero_whenBalance_thenAccountBalanceEqualsZero() {
        //Given
        Account account = new Account("11", 0.0);

        //When
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "balance not equals " + ACCOUNT_BALANCE_0);
    }

    @Test
    void withdraw() {
        //Given
        Account account = new Account("11", 0.0);

        //When
        double balance = account.withdraw(20);

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "balance not equals: " + ACCOUNT_BALANCE_0);

    }

    @Test
    void deposit() {
        //Given
        Account account = new Account(UniqueIdentifierGenerator.generateAccountNumber(), 0.0);

        //When
        double deposit = account.deposit(100);

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_100, deposit, "balance not equals: " + ACCOUNT_BALANCE_100);

    }
}