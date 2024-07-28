package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

public class DeleteLibraryItemCommand implements Command {
    private LibraryItemService libraryItemService;
    private int itemId;

    public DeleteLibraryItemCommand(LibraryItemService libraryItemService, int itemId) {
        this.libraryItemService = libraryItemService;
        this.itemId = itemId;
    }

    @Override
    public void execute() {
        synchronized (libraryItemService) {
            libraryItemService.deleteItem(itemId);
        }
    }
}
