package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateUserView.class.getResource("client.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Banking Transaction");
        stage.setScene(scene);
        stage.show();

        ClientController clientController = fxmlLoader.getController();
        clientController.setCurrentBalanceTextField();


        Parent rootNode = scene.getRoot();
        scene.setOnMouseClicked(event -> {
            rootNode.requestFocus();
            clientController.clearCurrentBalanceTextField();
        });
    }
}
