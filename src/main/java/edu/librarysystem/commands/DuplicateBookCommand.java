package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.services.LibraryItemService;

/**
 * The {@code DuplicateBookCommand} class implements the {@code Command} interface
 * and represents a command to duplicate a book in the library system.
 */
public class DuplicateBookCommand implements Command {
    private final LibraryItemService libraryItemService;
    private final int bookId;

    /**
     * Constructs a new {@code DuplicateBookCommand} with the specified parameters.
     *
     * @param libraryItemService the library item service to duplicate the book
     * @param bookId             the ID of the book to be duplicated
     */
    public DuplicateBookCommand(LibraryItemService libraryItemService, int bookId) {
        this.libraryItemService = libraryItemService;
        this.bookId = bookId;
    }

    /**
     * Executes the command to duplicate a book in the library system.
     */
    @Override
    public void execute() {
        LibraryItem item = libraryItemService.getItem(bookId);
        if (item instanceof Book) {
            libraryItemService.cloneBook(bookId);
        }
    }
}
