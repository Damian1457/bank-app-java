package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.exception.AccountException;

class TransferIntegrationTest {
    private static final double AMOUNT_10 = 10.0;

    @Test
    void transfer() throws AccountException {
        //Given
        Account accountFrom = new Account("11", 0.0);
        accountFrom.deposit(AMOUNT_10);

        Account accountTo = new Account("11", 0.0);

        //When
        TransferStatus transferStatus = Transfer.transfer(accountFrom, accountTo, AMOUNT_10);

        //Then
        Assertions.assertEquals(TransferStatus.OK, transferStatus, "transfer status is not equals: " + TransferStatus.OK);
    }

    @Test
    void transferAccountTo() throws AccountException {
        //Given
        Account accountFrom = new Account("11", 0.0);
        accountFrom.deposit(AMOUNT_10);

        Account accountTo = new Account("11", 0.0);

        //When
        TransferStatus transferStatus = Transfer.transfer(accountFrom, accountTo, AMOUNT_10);

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(TransferStatus.OK, transferStatus, "transfer status is not equals: " + TransferStatus.OK),
                () -> Assertions.assertEquals(AMOUNT_10, accountTo.balance(), "accountTo balance is not equals: " + AMOUNT_10)
        );
    }
}