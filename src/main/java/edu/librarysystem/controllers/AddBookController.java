package edu.librarysystem.controllers;
import edu.librarysystem.facade.LibraryFacade;

public class AddBookController {
    private LibraryFacade libraryFacade;

    public AddBookController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        libraryFacade.addBook(title, author, pages, isbn, yearPublished);
    }
}


