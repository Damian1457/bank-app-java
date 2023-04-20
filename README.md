## java-bank-app

>The project aims to create a simulation of a part of a banking application in Java. The main
goal of the project is to carry out the software development process, which consists of
analysis, design, implementation, testing and deployment.<br/><br/>
>The application allows users to create a bank, set up user accounts, deposit and withdraw
money, transfer funds from account to account, and read a list of current bank customers.
Each user is given a unique ID and bank account number, and all data is stored in a database.<br/><br/>
>The application's code is tested using JUnit, and it uses Maven and Git for project
management. It also uses an H2 or PostgreSQL database.
The project is under constant development, with plans to add new functionality in the future.
>### Technologies: Java 17, Maven, Git, H2 Database/PostgreSQL, JUnit.


## Navigation
>**1. [Application Model](#application-model)**<br/>
**2. [Application DAO](#application-dao)**<br/>
**3. [Installation](#installation)**<br/>
**4. [Configuration](#configuration)**<br/>
**5. [Usage](#usage)**

## Application Model


>This model consists of several classes representing different objects in a bank application:

>1. The Account class represents a bank account and has fields such as id, number, and balance, which describe the account number and its balance. It also has methods for withdrawing or depositing funds from the account. The toString method is used to return a text representation of the object. 

>2. The Address class represents the address where a bank's client is registered. It contains fields for the street, house number, and postal code. It has methods for setting and getting the field values, as well as a toString method.

>3. The Bank class represents a bank where clients can have their accounts. It has a name field representing the bank's name and an address field representing the bank's headquarters. It contains methods for registering a new client and returning a list of all clients registered with the bank. The toString method is used to return a text representation of the object.

>4. The Client class represents a bank client. It contains fields such as firstName, lastName, and address representing the client's name and address. It also has an accounts field that stores a list of the client's accounts. This class has methods for registering a new account, retrieving a list of all accounts, and finding an account by its number. The toString method is used to return a text representation of the object.

>5. The Transfer class represents a transfer operation between two Account objects. It has a transfer method which takes in two Account objects representing the account to transfer from and the account to transfer to, as well as the amount to be transferred. 
The transfer method first checks if the balance of the accountFrom is sufficient for the transfer. If it is, it withdraws the amount from the accountFrom and deposits it into the accountTo, and returns a TransferStatus of OK. If the balance of the accountFrom is insufficient, it returns a TransferStatus of FAILURE.

>6. The TransferStatus is an enum class in Java that represents the result of a transfer operation in a bank application. 
<br/><br/>It has three possible values:
<br/>OK: indicates that the transfer was successful.
<br/>FAILURE: indicates that the transfer failed for some reason, and includes a custom error message and error code.
<br/>ERROR: indicates that an error occurred during the transfer operation, but the reason for the error is not specified.
<br/><br/>The TransferStatus class has two fields: name and code, both of which are optional. The name field stores a custom error message for the FAILURE value, while the code field stores a custom error code for the same value.
<br/>The TransferStatus enum provides getter methods for both fields, which can be used to retrieve the custom message and code for the FAILURE value. In addition, the TransferStatus enum can be used to represent the status of a transfer operation in a more expressive and structured way. 

 >####  All public methods in these classes also have loggers on input and output.

## Application DAO
> The AccountDao class is a Java class that provides data access methods for working with bank accounts stored in an H2 database. It has several methods for performing CRUD (Create, Read, Update, Delete) operations on account data, as well as a method for listing all accounts in the database.

```java 
1. The clearDatabaseRecords() method clears all records in the "ACCOUNTS" table of the database.

public void clearDatabaseRecords() {
LOGGER.info("clearDatabaseRecords()");
try {
Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
Statement statement = connection.createStatement();
boolean execute = statement.execute("DELETE FROM ACCOUNTS;");
LOGGER.info("execute: " + execute);
} catch (SQLException e) {
e.printStackTrace();
}
LOGGER.info("clearDatabaseRecords(...)");
} 
```
```Java
  2. The create(Account account) method creates a new account in the database with the specified account number and balance. It generates a unique ID for the account using the UniqueIdGenerator class and returns the created account object.

  public Account create(Account account) throws AccountException {

        LOGGER.info("create(" + account + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa")) {
        LOGGER.info("" + connection);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(?, ?, ?);");
        preparedStatement.setInt(1, UniqueIdGenerator.getNextId(connection));
        preparedStatement.setString(2, account.getNumber());
        preparedStatement.setDouble(3, account.balance());
        int executeUpdate = preparedStatement.executeUpdate();
        LOGGER.info("create(...) = " + executeUpdate);

        } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Database error", e);
        }
        return account;
        }
```
```Java
  3. The read(int id) method retrieves an account with the specified ID from the database and returns it as an Account object. If no account with the specified ID is found in the database, it throws a ReadAccountException.

public Account read(int id) throws AccountException { 

        LOGGER.info("read(" + id + ")");

        try {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
        LOGGER.info("" + connection);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
        int accountId = resultSet.getInt("ID");
        String accountNumber = resultSet.getString("ACC_NO");
        double accountBalance = resultSet.getDouble("BALANCE");
        Account account = new Account(accountId, accountNumber, accountBalance);
        LOGGER.info("read(...) = " + account);
        return account;
        }

        } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Database error", e);
        throw new ReadAccountException("There is no account with this id number", e);
        }
        LOGGER.info("read(...) = " + null);
        return null;
        }
```
``` Java
  4. The update(Account account) method updates an existing account in the database with the values from the specified Account object. If no account with the specified ID is found in the database, it throws an UpdateAccountException.

public Account update(Account account) throws AccountException {
        LOGGER.info("update(" + account + ")");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ACCOUNTS SET ACC_NO=?, BALANCE=? WHERE ID=?")) {

            preparedStatement.setString(1, account.getNumber());
            preparedStatement.setDouble(2, account.balance());
            preparedStatement.setInt(3, account.getId());

            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("update(...) = " + executeUpdate);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new UpdateAccountException("No account to change", e);
        }
        return account;
    }
```
``` Java
  5. The delete(int id) method deletes the account with the specified ID from the database. If no account with the specified ID is found in the database, it throws a DeleteAccountException.

 public void delete(int id) throws AccountException {

        LOGGER.info("delete(" + id + ")");

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACCOUNTS WHERE ID=?");
            preparedStatement.setInt(1, id);

            int executeDelete = preparedStatement.executeUpdate();
            LOGGER.info("delete(...) = " + executeDelete);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new DeleteAccountException("There is no account with this id number", e);
        }
    }
```
``` Java
6. The list() method retrieves all accounts from the database and returns them as a list of Account objects. If the list is empty, it throws a ListAccountException.

 public List<Account> list() throws AccountException {
        List<Account> accounts = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNTS ORDER BY ID;");
            LOGGER.info("" + resultSet);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String number = resultSet.getString("ACC_NO");
                double balance = resultSet.getDouble("BALANCE");
                Account account = new Account(id, number, balance);
                accounts.add(account);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ListAccountException("The list of accounts is empty", e);
        }
        return accounts;
    }
```

>The class also includes a private LOGGER object for logging messages to the console. 
>The class uses JDBC (Java Database Connectivity) to communicate with the H2 database.
``` Java
private static final Logger LOGGER = Logger.getLogger(AccountDao.class.getName());
LOGGER.info("clearDatabaseRecords()");
LOGGER.info("clearDatabaseRecords(...)");
```
## Installation

>#### To run this application, you will need:
>- Java Development Kit (JDK) 11 or newer
>- Maven 3.6 or newer
>- H2 Database Engine 1.4.200 or newer

>#### Follow these steps to install and run the application:
>1. Clone this repository to your computer.
>2. Open a terminal and navigate to the directory containing the `pom.xml` file.
>3. Start the H2 Database Engine.

## Configuration

>#### The default database connection settings are:
>- URL: jdbc:h2:~/test
>- Username: sa
>- Password: sa<br/>
<br/>You can change these settings by editing the file `src/main/resources/application.properties`.

## Usage
>The application uses JDBC to connect to an H2 database. <br/>To use this application, you must have the H2 database and JDBC driver installed.
<br/>To use this application, call the methods of the `AccountDao` class. <br/>
<br/>Here is an example code:
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









