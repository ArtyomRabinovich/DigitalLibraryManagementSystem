package edu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;

public class LibraryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Adjust the path to reflect the package structure
        URL fxmlLocation = getClass().getResource("/edu/librarysystem/main.fxml");
        Objects.requireNonNull(fxmlLocation, "main.fxml resource not found in edu/librarysystem");

        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
