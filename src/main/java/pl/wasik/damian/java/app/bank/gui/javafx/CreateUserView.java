package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateUserView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateUserView.class.getResource("create-user.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Create User");
        stage.setScene(scene);
        stage.show();
    }
}
