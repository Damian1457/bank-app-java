package pl.wasik.damian.java.app.bank.gui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountList extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateAccountList.class.getResource("account-list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Accounts List");
        stage.setScene(scene);
        stage.show();
    }
}
