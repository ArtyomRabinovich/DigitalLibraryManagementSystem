package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

/**
 * The AddLibraryItemCommand class implements the Command interface and
 * represents a command to add a new library item to the library system.
 */
public class AddLibraryItemCommand implements Command {
    private final LibraryItemService libraryItemService;
    private final String title;
    private final String author;
    private final int pages;
    private final String isbn;
    private final int yearPublished;

    /**
     * Constructs a new AddLibraryItemCommand with the specified parameters.
     *
     * @param libraryItemService the library item service to add the item to.
     * @param title              the title of the library item.
     * @param author             the author of the library item.
     * @param pages              the number of pages in the library item.
     * @param isbn               the ISBN of the library item.
     * @param yearPublished      the year the library item was published.
     */
    public AddLibraryItemCommand(LibraryItemService libraryItemService, String title, String author, int pages, String isbn, int yearPublished) {
        this.libraryItemService = libraryItemService;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
    }

    /**
     * Executes the command to add a new library item to the library system.
     */
    @Override
    public void execute() {
        libraryItemService.addItem(title, author, pages, isbn, yearPublished);
    }
}
