package edu.librarysystem.controllers;

import edu.librarysystem.facade.LibraryFacade;

public class ReturnBookController {
    private LibraryFacade libraryFacade;

    public ReturnBookController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void returnBook(int bookId) {
        libraryFacade.returnBook(bookId);
    }
}
