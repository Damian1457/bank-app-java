package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController {

    private SceneManagerController sceneManager;

    public LoginController() {
        sceneManager = new SceneManagerController();
    }

    public void buttonSignUpOnAction(ActionEvent event) throws IOException {
        sceneManager.setScene(event, "create-client.fxml");
    }
}
