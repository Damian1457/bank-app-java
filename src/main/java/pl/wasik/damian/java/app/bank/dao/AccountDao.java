package pl.wasik.damian.java.app.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao {
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
    public void create() {

    }

    // R - read
    public void read() {
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST ORDER BY ID;");
            System.out.println(resultSet);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
