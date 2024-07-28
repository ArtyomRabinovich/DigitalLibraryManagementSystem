package edu.librarysystem.controllers;
import edu.librarysystem.facade.LibraryFacade;

public class AddMemberController {
    private LibraryFacade libraryFacade;

    public AddMemberController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void addMember(String name) {
        libraryFacade.addMember(name);
    }
}

