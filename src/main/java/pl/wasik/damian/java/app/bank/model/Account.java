package pl.wasik.damian.java.app.bank.model;

import pl.wasik.damian.java.app.bank.exception.AccountException;

import java.util.logging.Logger;

public class Account {
    private static final Logger LOGGER = Logger.getLogger(Account.class.getName());
    private int id;
    private String number;
    private double balance;

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public Account(int id, String number, double balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public double withdraw(double amount) throws AccountException {
        LOGGER.info("withdraw(" + amount + ")");
        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            throw new AccountException("Insufficient funds in the account.");
        }
        LOGGER.info("balance() = " + this.balance);
        return this.balance;
    }

    public double deposit(double amount) {
        LOGGER.info("deposit(" + amount + ")");
        balance += amount;
        LOGGER.info("balance() = " + this.balance);
        return this.balance;
    }

    public int getId() {
        return id;
    }

    public double balance() {
        return this.balance;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
