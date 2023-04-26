package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class CreateUserController {

    private static final Logger LOGGER = Logger.getLogger(CreateUserController.class.getName());

    @FXML
    public TextField nameCreateTextfield;

    @FXML
    public TextField surnameCreateTextField;

    @FXML
    public TextField streetCreateTextField;

    @FXML
    public TextField houseCreateTextField;

    @FXML
    public TextField postalCreateTextField;

    @FXML
    public TextField passwordCreateTextField;

    @FXML
    public TextField confirmPasswordCreateTextField;

    @FXML
    public Label labelLoginMessages;

    @FXML
    private ClientView clientView = new ClientView();

    @FXML
    private LoginView loginView;


    public void createAccountButtonOnAction(ActionEvent action) throws IOException {
        try {
            if (nameCreateTextfield.getText().isBlank() || surnameCreateTextField.getText().isBlank()
                    || streetCreateTextField.getText().isBlank() || houseCreateTextField.getText().isBlank()
                    || postalCreateTextField.getText().isBlank() || passwordCreateTextField.getText().isBlank()
                    || confirmPasswordCreateTextField.getText().isBlank()) {
                labelLoginMessages.setText("Please enter the all data!");
            } else {
                Stage currentStage = (Stage) ((Button) action.getSource()).getScene().getWindow();
                currentStage.close();

                clientView.start(new Stage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
