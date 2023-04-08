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

**2. [Account Class](#account-class)**<br/>

**3. [Installation](#installation)**<br/>
**4. [Configuration](#configuration)**<br/>
**5. [Usage](#usage)**

## AccountDao Class

This class contains methods for managing bank accounts in the database.

## Account Class

This class represents a bank account.


## Installation

To run this application, you will need:

- Java Development Kit (JDK) 11 or newer
- Maven 3.6 or newer
- H2 Database Engine 1.4.200 or newer

Follow these steps to install and run the application:

1. Clone this repository to your computer.
2. Open a terminal and navigate to the directory containing the `pom.xml` file.
3. Start the H2 Database Engine.

## Configuration

The default database connection settings are:

- URL: jdbc:h2:~/test
- Username: sa
- Password: sa

You can change these settings by editing the file `src/main/resources/application.properties`.

## Usage
### Ultimately, there will be a Web page view.

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









