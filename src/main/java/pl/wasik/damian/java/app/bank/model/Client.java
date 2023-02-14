package pl.wasik.damian.java.app.bank.model;

import pl.wasik.damian.java.app.bank.utils.UniqueIdentifierGenerator;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstName;
    private String lastName;
//    private String street;
//    private String houseNumber;
//    private String postalCode;
    private Address address;
    private List<Account> accounts = new ArrayList<>();

    public Client(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Account openAccount() {
//        String number = UUID.randomUUID().toString();
        String number = UniqueIdentifierGenerator.generateAccountNumber();
        Account account = new Account(number, 0.0);
        accounts.add(account);

        return account;
    }

    public List<Account> allAccounts() {
        return this.accounts;
    }
}
