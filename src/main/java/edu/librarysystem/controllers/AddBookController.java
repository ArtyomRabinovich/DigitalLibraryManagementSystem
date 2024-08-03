package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
        int pages = Integer.parseInt(pagesField.getText());
        String isbn = isbnField.getText();
        int yearPublished = Integer.parseInt(yearPublishedField.getText());

        librarian.addBook(title, author, pages, isbn, yearPublished);

        titleField.clear();
        authorField.clear();
        pagesField.clear();
        isbnField.clear();
        yearPublishedField.clear();
    }
}
