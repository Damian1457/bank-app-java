package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientController {

    @FXML
    public TextField currentBalanceTextField;

    @FXML
    private Button buttonLogOut;

    public void logoutButtonAction(ActionEvent event) {
        Stage stage = (Stage) buttonLogOut.getScene().getWindow();
        stage.close();
    }

    public void setCurrentBalanceTextField() {
        currentBalanceTextField.setText("0.00 PLN");
    }
}
