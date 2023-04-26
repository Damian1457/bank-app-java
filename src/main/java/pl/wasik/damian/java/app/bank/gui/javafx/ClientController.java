package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientController {

    private static final String DEFAULT_BALANCE_TEXT_0 = "0.00 PLN";

    @FXML
    public TextField amountTextField;

    @FXML
    public TextField accountToTextField;

    @FXML
    public TextField accountFromTextField;

    @FXML
    private TextField currentBalanceTextField;

    @FXML
    private Button buttonLogOut;

    public void logoutButtonAction(ActionEvent event) {
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        stage.close();
    }

    public void setCurrentBalanceTextField() {
        currentBalanceTextField.setText(DEFAULT_BALANCE_TEXT_0);
        currentBalanceTextField.setOnMouseClicked(event -> currentBalanceTextField.setText(""));
        accountFromTextField.setOnMouseClicked(event -> currentBalanceTextField.setText(DEFAULT_BALANCE_TEXT_0));
        accountToTextField.setOnMouseClicked(event -> currentBalanceTextField.setText(DEFAULT_BALANCE_TEXT_0));
        amountTextField.setOnMouseClicked(event -> currentBalanceTextField.setText(DEFAULT_BALANCE_TEXT_0));
    }

    public void clearCurrentBalanceTextField() {
        currentBalanceTextField.setText(DEFAULT_BALANCE_TEXT_0);
    }
}
