package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.services.LibraryItemService;

public class DuplicateBookCommand implements Command {
    private final LibraryItemService libraryItemService;
    private final int bookId;

    public DuplicateBookCommand(LibraryItemService libraryItemService, int bookId) {
        this.libraryItemService = libraryItemService;
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        LibraryItem item = libraryItemService.getItem(bookId);
        if (item instanceof Book) {
            libraryItemService.cloneBook(bookId);
        }
    }
}
