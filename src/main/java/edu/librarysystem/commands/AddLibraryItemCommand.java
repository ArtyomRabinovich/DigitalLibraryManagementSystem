package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

public class AddLibraryItemCommand implements Command {
    private LibraryItemService libraryItemService;
    private String title;
    private String author;
    private int pages;
    private String isbn;
    private int yearPublished;

    public AddLibraryItemCommand(LibraryItemService libraryItemService, String title, String author, int pages, String isbn, int yearPublished) {
        this.libraryItemService = libraryItemService;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
    }

    @Override
    public void execute() {
        synchronized (libraryItemService) {
            libraryItemService.addItem(title, author, pages, isbn, yearPublished);
        }
    }
}