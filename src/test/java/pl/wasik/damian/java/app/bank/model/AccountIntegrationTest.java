package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.AccountException;

public class AccountIntegrationTest {
    public static final int ACCOUNT_BALANCE_0 = 0;
    public static final int ACCOUNT_BALANCE_60 = 60;
    public static final int DEPOSIT_AMOUNT_0 = 0;
    public static final int DEPOSIT_AMOUNT_100 = 100;
    public static final int DEPOSIT_AMOUNT_170 = 170;
    public static final int WITHDRAW_AMOUNT_10 = 10;
    public static final int WITHDRAW_AMOUNT_40 = 40;
    public static final int WITHDRAW_AMOUNT_170 = 170;
    public static final String ACCOUNT_NUMBER = "11";

    @Test
    void givenAccountWithBalanceZero_whenDeposit100_thenAccountBalanceEquals60() throws AccountException {
        //Given
        Account account = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);

        //When
        account.deposit(DEPOSIT_AMOUNT_100);
        account.withdraw(WITHDRAW_AMOUNT_40);
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_60, balance, "balance not equals " + ACCOUNT_BALANCE_60);
    }

    @Test
    void givenAccountWithBalanceZero_whenDeposit0AndWithdraw10_thenShouldThrowException() throws AccountException {
        //Given
        Account account = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);

        //When & Then
        Assertions.assertThrows(AccountException.class, () -> {
            account.deposit(DEPOSIT_AMOUNT_0);
            account.withdraw(WITHDRAW_AMOUNT_10);
        }, "The exception was not thrown");
    }

    @Test
    void givenAccountWithBalanceZero_whenDeposit170AndWithdraw170_thenAccountBalanceEquals0() throws
            AccountException {
        //Given
        Account account = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);

        //When
        account.deposit(DEPOSIT_AMOUNT_170);
        account.withdraw(WITHDRAW_AMOUNT_170);
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "balance not equals " + ACCOUNT_BALANCE_0);
    }
}



