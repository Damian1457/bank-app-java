## java-bank-app

The project aims to create a simulation of a part of a banking application in Java. The main
goal of the project is to carry out the software development process, which consists of
analysis, design, implementation, testing and deployment.
The application allows users to create a bank, set up user accounts, deposit and withdraw
money, transfer funds from account to account, and read a list of current bank customers.
Each user is given a unique ID and bank account number, and all data is stored in a database.
The application's code is tested using JUnit, and it uses Maven and Git for project
management. It also uses an H2 or PostgreSQL database.
The project is under constant development, with plans to add new functionality in the future.
#### Technologies: Java 17, Maven, Git, H2 Database/PostgreSQL, JUnit.


## Navigation
**1. [AccountDao Class](#accountdao-class)**
> - [clearDatabaseRecords() method](#cleardatabaserecords--) 
> - [create(Account account) method](#create--account-account-) 
> - [read(int id) method ](#read--int-id-) 
> - [update(Account account) method](#update--account-account-) 
> - [delete(int id) method](#delete--int-id-)
> - [list() method ](#list--) 

**2. [Account Class](#account-class)**<br/>
> - [Account(String number, double balance) constructor](#account--string-number-double-balance-) 
> - [Account(int id, String number, double balance) constructor](#account--int-id-string-number-double-balance-) 
> - [withdraw(double amount) method](#withdraw--double-amount-)

**3. [Installation](#installation)**<br/>
**4. [Configuration](#configuration)**<br/>
**5. [Usage](#usage)**

## AccountDao Class

This class contains methods for managing bank accounts in the database.

### clearDatabaseRecords()

This method clears all records from the ACCOUNTS table in the database.

### create(Account account)

This method creates a new account in the database by inserting a new record into the ACCOUNTS table.

- `account`: an instance of the Account class to be added to the database.

### read(int id)

This method reads an account record from the ACCOUNTS table in the database.

- `id`: the id of the account to be read.

### update(Account account)

This method updates an existing account record in the ACCOUNTS table in the database.

- `account`: an instance of the Account class to be updated in the database.

### delete(int id)

This method deletes an account record from the ACCOUNTS table in the database.

- `id`: the id of the account to be deleted.

### list()

This method retrieves all account records from the ACCOUNTS table in the database and returns them as a list of Account objects.

## Account Class

This class represents a bank account.

### Account(String number, double balance)

This constructor creates a new account with the given account number and balance.

- `number`: the account number.
- `balance`: the account balance.

### Account(int id, String number, double balance)

This constructor creates a new account with the given id, account number, and balance.

- `id`: the account id.
- `number`: the account number.
- `balance`: the account balance.

### withdraw(double amount)

This method withdraws the given amount from the account balance.

- `amount`: the amount to be withdrawn.

## Installation

To run this application, you will need:

- Java Development Kit (JDK) 11 or newer
- Maven 3.6 or newer
- H2 Database Engine 1.4.200 or newer

Follow these steps to install and run the application:

1. Clone this repository to your computer.
2. Open a terminal and navigate to the directory containing the `pom.xml` file.
3. Run the command `mvn clean install` to compile the code and build the JAR file.
4. Start the H2 Database Engine.
5. Run the application by executing the command `java -jar target/account-1.0-SNAPSHOT.jar`.

## Configuration

The default database connection settings are:

- URL: jdbc:h2:~/test
- Username: sa
- Password: sa

You can change these settings by editing the file `src/main/resources/application.properties`.

## Usage // docelowo będzie web

The application uses JDBC to connect to an H2 database. To use this application, you must have the H2 database and JDBC driver installed.
o use this application, call the methods of the `AccountDao` class. Here is an example code:

```java
AccountDao accountDao = new AccountDao();

// Create a new account
Account account1 = new Account("123456789", 1000.0);
accountDao.create(account1);

// Read an account with a given ID
Account account2 = accountDao.read(1);

// Update an account
account2.setBalance(2000.0);
accountDao.update(account2);

// Delete an account
accountDao.delete(1);

// List all accounts
List<Account> accounts = accountDao.list();









