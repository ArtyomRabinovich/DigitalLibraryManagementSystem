package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class ReturnBookController {

    @FXML
    private TextField bookIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleReturnBook() {
        String bookIdText = bookIdField.getText();

        int bookId;
        try {
            if (bookIdText == null || bookIdText.trim().isEmpty()) {
                throw new NumberFormatException();
            }
            bookId = Integer.parseInt(bookIdText);
            if (bookId <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert();
            return;
        }

        librarian.returnBook(bookId);

        bookIdField.clear();

        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Book ID must be a positive integer.", ButtonType.OK);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
