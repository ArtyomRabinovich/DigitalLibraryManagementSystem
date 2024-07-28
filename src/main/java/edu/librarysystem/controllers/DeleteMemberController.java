package edu.librarysystem.controllers;

import edu.librarysystem.facade.LibraryFacade;

public class DeleteMemberController {
    private LibraryFacade libraryFacade;

    public DeleteMemberController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void deleteMember(int memberId) {
        libraryFacade.deleteMember(memberId);
    }
}
