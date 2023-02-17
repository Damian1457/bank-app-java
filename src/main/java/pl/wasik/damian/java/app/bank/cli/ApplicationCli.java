package pl.wasik.damian.java.app.bank.cli;

import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.model.Address;
import pl.wasik.damian.java.app.bank.model.Bank;
import pl.wasik.damian.java.app.bank.model.Client;
import pl.wasik.damian.java.app.bank.model.Transfer;
import pl.wasik.damian.java.app.bank.model.TransferStatus;

public class ApplicationCli {
    public static void main(String[] args) {
        System.out.println("Welcome in Bank");

        Address smallBankAddress = new Address("Boleslawa Limanowskiego", "10A", "05-660");
        Bank smallBank = new Bank("ING Bank", smallBankAddress);

        Address damianAddress = new Address("Powstancow", "21", "02-943");
        Client damianClient = smallBank.registerClient("Damian", "Wasik", damianAddress);

        Account damianAccount = damianClient.openAccount();
        damianAccount.deposit(1_000_000.00);
        double damianDeposit = damianAccount.deposit(50_000.00);

        System.out.println("Assets in Damian account: " + damianDeposit);

        Address aleksanderAddress = new Address("Powstancow", "21", "02-943");
        Client aleksanderClient = smallBank.registerClient("Aleksander", "Wasik", aleksanderAddress);

        Account aleksanderAccount = aleksanderClient.openAccount();

        TransferStatus damianAleksanderTransferStatus = Transfer.transfer(damianAccount, aleksanderAccount, 25_000.00);
        System.out.println("Transfer status: " + damianAleksanderTransferStatus);
        System.out.println("Assets after transfer in Damian account: " + damianAccount.balance());
    }
}
