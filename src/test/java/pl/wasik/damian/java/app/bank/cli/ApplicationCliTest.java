package pl.wasik.damian.java.app.bank.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.model.Address;
import pl.wasik.damian.java.app.bank.model.Bank;
import pl.wasik.damian.java.app.bank.model.Client;
import pl.wasik.damian.java.app.bank.model.Transfer;
import pl.wasik.damian.java.app.bank.model.TransferStatus;

class ApplicationCliTest {

    private static final double ACTUAL_BALANCE = 50.00;
    private static final TransferStatus TRANSFER_STATUS_OK = TransferStatus.OK;

    @Test
    void testApplication() {

        //Given
        ApplicationCli applicationCli = new ApplicationCli();
        Address smallBankAddress = new Address("street", "houseNumber", "postalCode");
        Bank smallBank = new Bank("bankName", smallBankAddress);
        Address damianAddress = new Address("damianStreet", "damianHouseNumber", "damianPostalCode");
        Address aleksanderAddress = new Address("aleksanderStreet", "aleksanderHouseNumber", "aleksanderPostalCode");

        //When
        Client damianClient = smallBank.registerClient("clientDamianName", "clientDamianLastName", damianAddress);
        Account damianAccount = damianClient.openAccount();
        damianAccount.deposit(100.00);
//        ApplicationCli.main(null); https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner

        Client aleksanderClient = smallBank.registerClient("clientAleksanderName", "clientAleksanderLastName", aleksanderAddress);
        Account aleksanderAccount = aleksanderClient.openAccount();

        TransferStatus damianAleksanderTransferStatus = Transfer.transfer(damianAccount, aleksanderAccount, 50.00);
        double aleksanderBalance = aleksanderAccount.balance();

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(aleksanderBalance, ACTUAL_BALANCE, " aleksanderBalance not equals 50.00"),
                () -> Assertions.assertEquals(TRANSFER_STATUS_OK, damianAleksanderTransferStatus, " transfer status not equals OK")
        );
    }

}