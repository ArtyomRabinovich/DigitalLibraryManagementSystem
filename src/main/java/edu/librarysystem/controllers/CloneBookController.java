package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class CloneBookController {

    @FXML
    private TextField bookIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleCloneBook() {
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
            showAlert("Invalid Input", "Book ID must be a positive integer.");
            return;
        }

        if (!librarian.duplicateBook(bookId)) {
            showAlert("Cannot Clone", "The book is currently loaned out and cannot be cloned.");
            return;
        }

        bookIdField.clear();

        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
