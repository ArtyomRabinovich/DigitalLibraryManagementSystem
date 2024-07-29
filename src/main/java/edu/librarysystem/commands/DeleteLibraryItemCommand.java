package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

/**
 * The DeleteLibraryItemCommand class implements the Command interface and
 * represents a command to delete a library item from the library system.
 */
public class DeleteLibraryItemCommand implements Command {
    private final LibraryItemService libraryItemService;
    private final int itemId;

    /**
     * Constructs a new DeleteLibraryItemCommand with the specified parameters.
     *
     * @param libraryItemService the library item service to delete the item from.
     * @param itemId             the ID of the library item to be deleted.
     */
    public DeleteLibraryItemCommand(LibraryItemService libraryItemService, int itemId) {
        this.libraryItemService = libraryItemService;
        this.itemId = itemId;
    }

    /**
     * Executes the command to delete a library item from the library system.
     */
    @Override
    public void execute() {
        libraryItemService.deleteItem(itemId);
    }
}
