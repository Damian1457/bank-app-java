package pl.wasik.damian.java.app.bank.model;

import pl.wasik.damian.java.app.bank.utils.UniqueIdentifierGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
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
        LOGGER.info("openAccount(" + firstName + ", " + lastName + ")");
//        String number = UUID.randomUUID().toString();
        String number = UniqueIdentifierGenerator.generateAccountNumber();
        Account account = new Account(number, 0.0);
        accounts.add(account);
        LOGGER.info("openAccount() = " + account);

        return account;
    }

    public List<Account> allAccounts() {
        LOGGER.info("allAccounts()");
        LOGGER.info("allAccounts(...) = " + this.accounts);
        return this.accounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
