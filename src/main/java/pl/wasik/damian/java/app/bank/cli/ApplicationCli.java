package pl.wasik.damian.java.app.bank.cli;

import pl.wasik.damian.java.app.bank.model.Account;
import pl.wasik.damian.java.app.bank.model.Address;
import pl.wasik.damian.java.app.bank.model.Bank;
import pl.wasik.damian.java.app.bank.model.Client;
import pl.wasik.damian.java.app.bank.model.Transfer;
import pl.wasik.damian.java.app.bank.model.TransferStatus;

import java.util.Scanner;

public class ApplicationCli {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome in Bank");

        System.out.println("Please enter bank details");
        System.out.println("Street name: ");
        String street = sc.next();
        System.out.println("House number: ");
        String houseNumber = sc.next();
        System.out.println("Postal code: ");
        String postalCode = sc.next();
        Address smallBankAddress = new Address(street, houseNumber, postalCode);
        System.out.println("The bank address is: " + smallBankAddress);

        System.out.println("Please enter the name of the bank: ");
        String bankName = sc.next();
        Bank smallBank = new Bank(bankName, smallBankAddress);
        System.out.println("The bank's details are: " + smallBank);

        System.out.println("Damian enter your details: ");
        System.out.println("Street name: ");
        String damianStreet = sc.next();
        System.out.println("House number: ");
        String damianHouseNumber = sc.next();
        System.out.println("Postal code: ");
        String damianPostalCode = sc.next();
        Address damianAddress = new Address(damianStreet, damianHouseNumber, damianPostalCode);
        System.out.println("Please enter your name: ");
        String clientDamianName = sc.next();
        System.out.println("Please enter your surname: ");
        String clientDamianLastName = sc.next();
        Client damianClient = smallBank.registerClient(clientDamianName, clientDamianLastName, damianAddress);
        System.out.println("Client data are: " + damianClient);

        Account damianAccount = damianClient.openAccount();
        System.out.println("Please enter the amount you wish to deposit: ");
        double amount = sc.nextDouble();
        damianAccount.deposit(amount);
        double damianDeposit = damianAccount.deposit(amount);
        System.out.println("Assets in Damian account: " + damianDeposit);

        System.out.println("Aleksander enter your details: ");
        System.out.println("Street name: ");
        String aleksanderStreet = sc.next();
        System.out.println("House number: ");
        String aleksanderHouseNumber = sc.next();
        System.out.println("Postal code: ");
        String aleksanderPostalCode = sc.next();
        Address aleksanderAddress = new Address(aleksanderStreet, aleksanderHouseNumber,aleksanderPostalCode);
        System.out.println("Please enter your name: ");
        String clientAleksanderName = sc.next();
        System.out.println("Please enter your surname: ");
        String clientAleksanderLastName = sc.next();
        Client aleksanderClient = smallBank.registerClient(clientAleksanderName, clientAleksanderLastName, aleksanderAddress);
        Account aleksanderAccount = aleksanderClient.openAccount();
        System.out.println("Client data are: " + aleksanderAccount);

        System.out.println("Please enter the amount you want to transfer: ");
        double transfer = sc.nextDouble();
        TransferStatus damianAleksanderTransferStatus = Transfer.transfer(damianAccount, aleksanderAccount, transfer);
        System.out.println("Transfer status: " + damianAleksanderTransferStatus);
        System.out.println("Assets after transfer in Damian account: " + damianAccount.balance());
    }
    //app
}

