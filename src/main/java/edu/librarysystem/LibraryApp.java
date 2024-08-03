package edu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;

/**
 * The LibraryApp class is the main entry point for the Library Management System application.
 * It extends the JavaFX Application class and sets up the primary stage.
 */
public class LibraryApp extends Application {

    /**
     * The start method is called when the application is launched. It sets up the primary stage
     * with the main.fxml layout.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     * @throws Exception if there is any issue loading the main.fxml resource.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/edu/librarysystem/main.fxml");
        Objects.requireNonNull(fxmlLocation, "main.fxml resource not found in edu/librarysystem");

        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main method serves as the entry point for the Java application.
     * It launches the JavaFX application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
