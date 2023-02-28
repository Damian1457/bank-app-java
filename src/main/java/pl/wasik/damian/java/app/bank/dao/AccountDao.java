package pl.wasik.damian.java.app.bank.dao;

import pl.wasik.damian.java.app.bank.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDao {
    private static final Logger LOGGER = Logger.getLogger(AccountDao.class.getName());

    //Przepis na korzystanie z JDBC
    //1. DriverManager
    //2. Connection
    //3. Statement/PrepareStatement
    //4. ResultSet
    // TODO: 17.02.2023 Za pomocą h2console stworzyć nową tabelę ANIMALS
    //Wypełnić tabelę ANIMALS danymi, dodać 2 rekordy
    //Stworzyć nową klasę AnimalDao i dodać implementacje metody list analogicznie jak w AccountDao
    //https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html

    // C - create
//    public void create(int id, String accountNumber, double balance) {
    public void create(Account account) {
        LOGGER.info("create(" + account + ")");
        //INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(1, '12121212', 20.0);
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            LOGGER.info("" + connection);
//            Statement statement = connection.createStatement();
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(10, '101111111111', 50.0);");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID, ACC_NO, BALANCE) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setString(2, account.getNumber());
            preparedStatement.setDouble(3, account.balance());
            int executeUpdate = preparedStatement.executeUpdate();
            LOGGER.info("create(...) = " + executeUpdate);

        } catch (SQLException e) {
//            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Database error", e);
        }
    }

    // R - read
    public void read() {
        //SELECT * FROM ACCOUNTS WHERE ID=9;
        // TODO: 28.02.2023 Wzorując się na metodzie create() zaimplementować zapytanie SELECT * FROM ACCOUNTS WHERE ID=9;
        // Metody publiczne logowanie wejścia i wyjścia, oraz poprawne logowanie wyjątków.
    }

    // U - update
    public void update() {
    }

    // D - delete
    public void delete() {
    }

    // L - list
    public void list() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println(connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNTS ORDER BY ID;");
            System.out.println(resultSet);
            while (resultSet.next()) {
                String name = resultSet.getString("ACC_NO");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
