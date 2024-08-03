package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class DeleteMemberController {

    @FXML
    private TextField memberIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleDeleteMember() {
        String memberIdText = memberIdField.getText();

        int memberId;
        try {
            if (memberIdText == null || memberIdText.trim().isEmpty()) {
                throw new NumberFormatException();
            }
            memberId = Integer.parseInt(memberIdText);
            if (memberId <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Member ID must be a positive integer.");
            return;
        }

        // Attempt to delete the member
        if (!librarian.deleteMember(memberId)) {
            showAlert("Cannot Delete", "The member is currently loaning books and cannot be deleted.");
            return;
        }

        // Clear the field
        memberIdField.clear();

        // Close the popup
        Stage stage = (Stage) memberIdField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
