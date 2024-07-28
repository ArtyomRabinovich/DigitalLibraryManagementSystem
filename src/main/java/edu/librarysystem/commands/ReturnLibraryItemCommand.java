package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

public class ReturnLibraryItemCommand implements Command {
    private LibraryItemService libraryItemService;
    private int itemId;

    public ReturnLibraryItemCommand(LibraryItemService libraryItemService, int itemId) {
        this.libraryItemService = libraryItemService;
        this.itemId = itemId;
    }

    @Override
    public void execute() {
        synchronized (libraryItemService) {
            libraryItemService.returnItem(itemId);
        }
    }
}
