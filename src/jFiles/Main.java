package jFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent loginRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/views/dashboard.fxml")));
        Scene login = new Scene(loginRoot);

        primaryStage.setTitle("Jeff's Photo Library");
        primaryStage.setScene(login);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
