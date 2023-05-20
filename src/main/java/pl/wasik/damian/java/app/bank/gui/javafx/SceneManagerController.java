package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManagerController {

    private final SceneFactory sceneFactory = new SceneFactory();

    public void setScene(ActionEvent event, String fxmlFileName) throws IOException {
        Scene scene = sceneFactory.getScene(fxmlFileName);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
