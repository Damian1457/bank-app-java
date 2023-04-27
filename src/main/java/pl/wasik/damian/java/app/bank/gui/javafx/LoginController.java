package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button goAdminButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private CreateUserView createUser = new CreateUserView();

    @FXML
    private CreateAccountList accountList = new CreateAccountList();

    public void setSignUpButtonOnAction() throws IOException {
        try {
            Stage currentStage = (Stage) signUpButton.getScene().getWindow();
            currentStage.close();

            createUser.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginButtonAction(ActionEvent event) throws SQLException {
        if (userNameTextField.getText().isBlank() || passwordTextField.getText().isBlank()) {
            loginMessageLabel.setText("Please enter a username and password!");
        } else {
            validateLogin();
        }
    }

    public void validateLogin() throws SQLException {

        LOGGER.info("validateLogin()");

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");

            preparedStatement.setString(1, userNameTextField.getText());
            preparedStatement.setString(2, passwordTextField.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loginMessageLabel.setText("Login successful!");
            } else {
                loginMessageLabel.setText("Invalid username or password!");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            e.printStackTrace();
        }
    }

    public void adminButtonOnAction() throws IOException {
        try {
            Stage currentStage = (Stage) goAdminButton.getScene().getWindow();
            currentStage.close();

            accountList.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}