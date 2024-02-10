package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.AccountException;
import pl.wasik.damian.java.app.bank.utils.AccountNumberGenerator;

class AccountTest {
    public static final int ACCOUNT_BALANCE_0 = 0;
    public static final int ACCOUNT_BALANCE_20 = 20;
    public static final int ACCOUNT_BALANCE_100 = 100;
    public static final int AMOUNT_WITHDRAW_30 = 30;
    public static final String ACCOUNT_NUMBER = "11";

    @Test
    void givenAccountWithBalanceZero_whenBalance_thenAccountBalanceEqualsZero() {
        //Given
        Account account = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);

        //When
        double balance = account.balance();

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "Balance not equals " + ACCOUNT_BALANCE_0);
    }

    @Test
    void givenAccountWithBalanceTwenty_whenWithdrawMoney_thenAccountBalanceEqualsZero() throws AccountException {
        //Given
        Account account = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_20);

        //When
        double balance = account.withdraw(ACCOUNT_BALANCE_20);

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_0, balance, "Balance not equals: " + ACCOUNT_BALANCE_0);
    }

    @Test
    void givenAccountWithBalanceTwenty_whenWithdrawFundsGreaterThanTheAccountBalance_thenShouldThrowException() {
        //Given
        Account account = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_20);

        //When & Then
        Assertions.assertThrows(AccountException.class, () -> account.withdraw(AMOUNT_WITHDRAW_30), "The exception was not thrown");
    }

    @Test
    void givenAccountWithZeroBalance_whenDepositFunds_thenBalanceShouldIncrease() {
        //Given
        Account account = new Account(AccountNumberGenerator.generate(), 0.0);

        //When
        double deposit = account.deposit(100);

        //Then
        Assertions.assertEquals(ACCOUNT_BALANCE_100, deposit, "Balance not equals: " + ACCOUNT_BALANCE_100);
    }
}