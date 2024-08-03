package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class AddBookController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField pagesField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField yearPublishedField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleAddBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String pagesText = pagesField.getText();
        String isbn = isbnField.getText();
        String yearPublishedText = yearPublishedField.getText();

        // Validate integer fields and ensure non-negative values
        int pages;
        int yearPublished;
        try {
            pages = Integer.parseInt(pagesText);
            yearPublished = Integer.parseInt(yearPublishedText);
            if (pages < 0 || yearPublished < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Pages and Year Published must be non-negative integers.");
            return;
        }

        // Check for empty fields
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            showAlert("Missing Information", "Please fill in all fields.");
            return;
        }

        // Add the book to the librarian's collection
        librarian.addBook(title, author, pages, isbn, yearPublished);

        // Clear fields
        titleField.clear();
        authorField.clear();
        pagesField.clear();
        isbnField.clear();
        yearPublishedField.clear();

        // Close the popup
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
