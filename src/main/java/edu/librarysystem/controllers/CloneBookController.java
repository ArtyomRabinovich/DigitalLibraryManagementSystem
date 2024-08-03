package edu.librarysystem.controllers;

import edu.librarysystem.models.Book;
import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CloneBookController {

    @FXML
    private TextField bookIdField;


    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleCloneBook() {
        int bookId = Integer.parseInt(bookIdField.getText());
        librarian.duplicateBook(bookId);
        bookIdField.clear();
    }
}
