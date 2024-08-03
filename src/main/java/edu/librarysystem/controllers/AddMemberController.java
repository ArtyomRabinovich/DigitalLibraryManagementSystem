package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class AddMemberController {

    @FXML
    private TextField memberNameField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleAddMember() {
        String memberName = memberNameField.getText();

        // Validate non-empty and letter-only member name
        if (memberName == null || memberName.trim().isEmpty() || !memberName.matches("[a-zA-Z]+")) {
            showAlert();
            return;
        }

        // Add the member to the librarian's collection
        librarian.addMember(memberName);

        // Clear the field
        memberNameField.clear();

        // Close the popup
        Stage stage = (Stage) memberNameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Member name must contain only letters and cannot be empty.", ButtonType.OK);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
