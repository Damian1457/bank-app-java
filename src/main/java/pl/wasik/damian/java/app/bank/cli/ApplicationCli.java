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

        System.out.println("Welcome in Bank");

        System.out.println("Please enter bank details");
        System.out.println("Street name: ");
        String street = ApplicationCli.getInput();
        System.out.println("House number: ");
        String houseNumber = ApplicationCli.getInput();
        System.out.println("Postal code: ");
        String postalCode = ApplicationCli.getInput();
        Address smallBankAddress = new Address(street, houseNumber, postalCode);
        System.out.println("The bank address is: " + smallBankAddress);

        System.out.println("Please enter the name of the bank: ");
        String bankName = ApplicationCli.getInput();
        Bank smallBank = new Bank(bankName, smallBankAddress);
        System.out.println("The bank's details are: " + smallBank);

        System.out.println("Damian enter your details: ");
        System.out.println("Street name: ");
        String damianStreet = ApplicationCli.getInput();
        System.out.println("House number: ");
        String damianHouseNumber = ApplicationCli.getInput();
        System.out.println("Postal code: ");
        String damianPostalCode = ApplicationCli.getInput();
        Address damianAddress = new Address(damianStreet, damianHouseNumber, damianPostalCode);
        System.out.println("Please enter your name: ");
        String clientDamianName = ApplicationCli.getInput();
        System.out.println("Please enter your surname: ");
        String clientDamianLastName = ApplicationCli.getInput();
        Client damianClient = smallBank.registerClient(clientDamianName, clientDamianLastName, damianAddress);
        System.out.println("Client data are: " + damianClient);

        Account damianAccount = damianClient.openAccount();
        System.out.println("Please enter the amount you wish to deposit: ");
        double amount = Double.parseDouble(ApplicationCli.getInput());
        double damianDeposit = damianAccount.deposit(amount);
        System.out.println("Assets in Damian account: " + damianDeposit);

        System.out.println("Aleksander enter your details: ");
        System.out.println("Street name: ");
        String aleksanderStreet = ApplicationCli.getInput();
        System.out.println("House number: ");
        String aleksanderHouseNumber = ApplicationCli.getInput();
        System.out.println("Postal code: ");
        String aleksanderPostalCode = ApplicationCli.getInput();
        Address aleksanderAddress = new Address(aleksanderStreet, aleksanderHouseNumber,aleksanderPostalCode);
        System.out.println("Please enter your name: ");
        String clientAleksanderName = ApplicationCli.getInput();
        System.out.println("Please enter your surname: ");
        String clientAleksanderLastName = ApplicationCli.getInput();
        Client aleksanderClient = smallBank.registerClient(clientAleksanderName, clientAleksanderLastName, aleksanderAddress);
        Account aleksanderAccount = aleksanderClient.openAccount();
        System.out.println("Client data are: " + aleksanderAccount);

        System.out.println("Please enter the amount you want to transfer: ");
        double transfer = Double.parseDouble(ApplicationCli.getInput());
        TransferStatus damianAleksanderTransferStatus = Transfer.transfer(damianAccount, aleksanderAccount, transfer);
        System.out.println("Transfer status: " + damianAleksanderTransferStatus);
        System.out.println("Assets after transfer in Damian account: " + damianAccount.balance());
    }
    //app

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}


