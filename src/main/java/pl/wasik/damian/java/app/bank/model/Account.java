package pl.wasik.damian.java.app.bank.model;

import java.util.logging.Logger;

public class Account {
    private static final Logger LOGGER = Logger.getLogger(Account.class.getName());
    private int id;
    private String number;
    //    private double balance = 10.0;
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

    public double withdraw(double amount) {
        //this.balance = this.balance - amount;
        LOGGER.info("withdraw(" + amount + ")");
        if (amount <= this.balance) {
            this.balance -= amount;
        }
//        else {
//            System.out.println(TransferStatus.FAILURE + " because you don't have money!");
//        }
        LOGGER.info("balance() = " + this.balance);

        return this.balance;
    }

    /**
     * This method deposit the given amount to the account balance.
     *
     * @param amount the amount to deposit
     * @return account balance after deposit
     */
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
/*
    CREATE TABLE ACCOUNTS(
    ID INT PRIMARY KEY,
    ACC_NO VARCHAR(255),
    BALANCE FLOAT
    );

ALTER TABLE CLIENTS
ADD ADDRESS_ID INTEGER;

ALTER TABLE CLIENTS
ADD CONSTRAINT FK_CLIENT_ADDRESS
FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ID);

SELECT *
FROM CLIENTS C
JOIN ADDRESS A
ON C.ADDRESS_ID = A.ID;

SELECT * FROM ADDRESS;
SELECT * FROM CLIENTS;

INSERT INTO ADDRESS( ID, STREET , HOUSE_NUMBER , POSTAL_CODE ) VALUES(4, 'Warecka', '21', '02-111');
INSERT INTO CLIENTS(ID, NAME, SURNAME, ADDRESS_ID) VALUES(8, 'Jacek', 'Jabłonka', 4);


--public class Account {
--    private String number;
--    private double balance;
--}
     */

}
