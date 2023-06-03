package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.AccountException;

class TransferIntegrationTest {
    private static final double AMOUNT_10 = 10.0;
    private static final double ACCOUNT_BALANCE_0 = 0.0;
    private static final String ACCOUNT_NUMBER = "11";

    @Test
    void givenSourceAccountWithBalance_whenTransferToDestinationAccount_thenTransferStatusIsOK() throws AccountException {
        //Given
        Account accountFrom = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);
        accountFrom.deposit(AMOUNT_10);

        Account accountTo = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);

        //When
        TransferStatus transferStatus = Transfer.transfer(accountFrom, accountTo, AMOUNT_10);

        //Then
        Assertions.assertEquals(TransferStatus.OK, transferStatus, "Transfer status is not equals: " + TransferStatus.OK);
    }

    @Test
    void givenSourceAccountWithBalance_whenTransferToDestinationAccount_thenTransferStatusIsOKAndDestinationAccountBalanceIsUpdated() throws AccountException {
        //Given
        Account accountFrom = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);
        accountFrom.deposit(AMOUNT_10);

        Account accountTo = new Account(ACCOUNT_NUMBER, ACCOUNT_BALANCE_0);

        //When
        TransferStatus transferStatus = Transfer.transfer(accountFrom, accountTo, AMOUNT_10);

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(TransferStatus.OK, transferStatus, "Transfer status is not equals: " + TransferStatus.OK),
                () -> Assertions.assertEquals(AMOUNT_10, accountTo.balance(), "AccountTo balance is not equals: " + AMOUNT_10)
        );
    }
}