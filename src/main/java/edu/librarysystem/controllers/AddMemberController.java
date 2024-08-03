package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddMemberController {

    @FXML
    private TextField memberNameField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleAddMember() {
        String memberName = memberNameField.getText();
        librarian.addMember(memberName);
        memberNameField.clear();
    }
}
