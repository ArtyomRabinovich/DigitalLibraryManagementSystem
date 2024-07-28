package edu.librarysystem.controllers;

import edu.librarysystem.facade.LibraryFacade;

public class DeleteBookController {
    private LibraryFacade libraryFacade;

    public DeleteBookController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void deleteBook(int bookId) {
        libraryFacade.deleteBook(bookId);
    }
}
