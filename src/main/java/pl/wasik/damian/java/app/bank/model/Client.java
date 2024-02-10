package pl.wasik.damian.java.app.bank.model;

import pl.wasik.damian.java.app.bank.utils.AccountNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    private int id;
    private String firstName;
    private String lastName;
    private Address address; // ADDRESS_ID -> Foreign Key
    private List<Account> accounts = new ArrayList<>();

    public Client(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account openAccount() {
        LOGGER.info("openAccount(" + firstName + ", " + lastName + ")");
        String number = AccountNumberGenerator.generate();
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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
