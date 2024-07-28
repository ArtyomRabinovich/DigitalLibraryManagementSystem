package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

public class LoanLibraryItemCommand implements Command {
    private LibraryItemService libraryItemService;
    private int itemId;
    private int memberId;

    public LoanLibraryItemCommand(LibraryItemService libraryItemService, int itemId, int memberId) {
        this.libraryItemService = libraryItemService;
        this.itemId = itemId;
        this.memberId = memberId;
    }

    @Override
    public void execute() {
        synchronized (libraryItemService) {
            libraryItemService.loanItem(itemId, memberId);
        }
    }
}
