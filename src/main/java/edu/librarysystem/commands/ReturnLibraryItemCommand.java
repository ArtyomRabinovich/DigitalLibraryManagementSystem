package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

/**
 * The ReturnLibraryItemCommand class implements the Command interface and
 * represents a command to return a library item to the library system.
 */
public class    ReturnLibraryItemCommand implements Command {
    private final LibraryItemService libraryItemService;
    private final int itemId;

    /**
     * Constructs a new ReturnLibraryItemCommand with the specified parameters.
     *
     * @param libraryItemService the library item service to return the item to.
     * @param itemId             the ID of the library item to be returned.
     */
    public ReturnLibraryItemCommand(LibraryItemService libraryItemService, int itemId) {
        this.libraryItemService = libraryItemService;
        this.itemId = itemId;
    }

    /**
     * Executes the command to return a library item to the library system.
     */
    @Override
    public void execute() {
        libraryItemService.returnItem(itemId);
    }
}
