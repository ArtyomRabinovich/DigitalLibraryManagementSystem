package edu.librarysystem.controllers;

import edu.librarysystem.facade.LibraryFacade;

public class LoanBookController {
    private LibraryFacade libraryFacade;

    public LoanBookController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void loanBook(int bookId, int memberId) {
        libraryFacade.loanBook(bookId, memberId);
    }
}
