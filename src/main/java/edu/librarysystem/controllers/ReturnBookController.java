package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ReturnBookController {

    @FXML
    private TextField bookIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleReturnBook() {
        int bookId = Integer.parseInt(bookIdField.getText());

        librarian.returnBook(bookId);

        bookIdField.clear();
    }
}
