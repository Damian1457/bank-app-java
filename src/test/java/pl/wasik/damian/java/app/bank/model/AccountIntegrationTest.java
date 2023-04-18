package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.AccountException;

public class AccountIntegrationTest {
    public static final int ACCOUNT_BALANCE_0 = 0; //Stałej
    public static final int ACCOUNT_BALANCE_60 = 60; //Stałej

    @Test
    void givenAccountWithBalanceZero_whenDeposit100_thenAccountBalanceEquals60() throws AccountException {
        //Given
        Account account = new Account("11", 0.0);

        //When
        account.deposit(100);
        account.withdraw(40);
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_60, balance, "balance not equals " + ACCOUNT_BALANCE_60);
    }

    @Test
    void givenAccountWithBalanceZero_whenDeposit0AndWithdraw10_thenAccountBalanceEquals0() throws AccountException {
        //Given
        Account account = new Account("11", 0.0);

        //When
        account.deposit(0);
        account.withdraw(10);
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "balance not equals " + ACCOUNT_BALANCE_0);
        //How can I use a transfer status in this case?
    }

    @Test
    void givenAccountWithBalanceZero_whenDeposit170AndWithdraw170_thenAccountBalanceEquals0() throws AccountException {
        //Given
        Account account = new Account("11", 0.0);

        //When
        account.deposit(170);
        account.withdraw(170);
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "balance not equals " + ACCOUNT_BALANCE_0);
    }
}



