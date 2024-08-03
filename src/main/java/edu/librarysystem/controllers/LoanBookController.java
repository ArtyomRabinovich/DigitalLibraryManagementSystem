package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoanBookController {

    @FXML
    private TextField bookIdField;
    @FXML
    private TextField memberIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleLoanBook() {
        int bookId = Integer.parseInt(bookIdField.getText());
        int memberId = Integer.parseInt(memberIdField.getText());

        librarian.loanBook(bookId, memberId);

        bookIdField.clear();
        memberIdField.clear();
    }
}
