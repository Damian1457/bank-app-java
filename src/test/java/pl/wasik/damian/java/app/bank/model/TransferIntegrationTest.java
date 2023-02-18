package pl.wasik.damian.java.app.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransferIntegrationTest {
    private static final double AMOUNT_10 = 10.0;

    @Test
    void transfer() {
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
    void transferAccountTo() {
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