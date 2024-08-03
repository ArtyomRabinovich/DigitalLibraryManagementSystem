package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class LoanBookController {

    @FXML
    private TextField bookIdField;
    @FXML
    private TextField memberIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleLoanBook() {
        String bookIdText = bookIdField.getText();
        String memberIdText = memberIdField.getText();

        // Validate non-empty and positive integer fields for bookId and memberId
        int bookId;
        int memberId;
        try {
            if (bookIdText == null || bookIdText.trim().isEmpty() || memberIdText == null || memberIdText.trim().isEmpty()) {
                throw new NumberFormatException();
            }
            bookId = Integer.parseInt(bookIdText);
            memberId = Integer.parseInt(memberIdText);
            if (bookId <= 0 || memberId <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert();
            return;
        }

        // Loan the book
        librarian.loanBook(bookId, memberId);

        // Clear the fields
        bookIdField.clear();
        memberIdField.clear();

        // Close the popup
        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Book ID and Member ID must be positive integers.", ButtonType.OK);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
