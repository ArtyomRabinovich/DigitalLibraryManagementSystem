package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteBookController {

    @FXML
    private TextField bookIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleDeleteBook() {
        int bookId = Integer.parseInt(bookIdField.getText());

        librarian.deleteBook(bookId);

        bookIdField.clear();
    }
}
