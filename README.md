# bank-app-java

## Description

The project aims to create a simulation of a part of a banking application in Java. The main goal of the project is to carry out the software development process, which consists of analysis, design, implementation, testing, and deployment.

The application allows users to create a bank, set up user accounts, deposit and withdraw money, transfer funds from account to account, and read a list of current bank customers. Each user is given a unique ID and bank account number, and all data is stored in a database.

The application's code is tested using JUnit, and it uses Maven and Git for project management. It also uses an H2 or PostgreSQL database. The project is under constant development, with plans to add new functionality in the future.

## Technologies/Versions

- Java: 17
- Maven: 3.10.1
- JUnit Jupiter: 5.9.2
- H2 Database: 2.1.214
- JDBC
- PostgreSQL
- Git

## Configuration

Before running the application, you need to configure the H2 database.

## Running / Installation

To run the application, follow these steps:

1. **Clone the repository**: Clone the repository to your computer using the following command:

    ```bash
    git clone https://github.com/Damian1457/bank-app-java.git
    ```
2. **Run the application**: Run the tests file to start the application. The tests should automatically start the application and verify its functionality.

3. **H2 Database Configuration**: Before running the application, you need to configure the H2 database by adding the following parameters to the `application.properties` file.

After completing these steps, the application should be running in IntelliJ IDEA, and the login page will be available. If you encounter any errors, make sure your environment is properly configured, and all dependencies are downloaded and installed.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.



