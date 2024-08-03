package edu.librarysystem.controllers;

import edu.librarysystem.singleton.Librarian;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteMemberController {

    @FXML
    private TextField memberIdField;

    private final Librarian librarian = Librarian.getInstance();

    @FXML
    private void handleDeleteMember() {
        int memberId = Integer.parseInt(memberIdField.getText());

        librarian.deleteMember(memberId);

        memberIdField.clear();
    }
}
