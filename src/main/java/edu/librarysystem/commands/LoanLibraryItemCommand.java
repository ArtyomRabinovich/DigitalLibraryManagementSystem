package edu.librarysystem.commands;

import edu.librarysystem.interfaces.Command;
import edu.librarysystem.services.LibraryItemService;

/**
 * The {@code LoanLibraryItemCommand} class implements the {@code Command} interface
 * and represents a command to loan a library item to a member in the library system.
 */
public class LoanLibraryItemCommand implements Command {
    private final LibraryItemService libraryItemService;
    private final int itemId;
    private final int memberId;

    /**
     * Constructs a new {@code LoanLibraryItemCommand} with the specified parameters.
     *
     * @param libraryItemService the library item service to loan the item from
     * @param itemId             the ID of the library item to be loaned
     * @param memberId           the ID of the member to loan the item to
     */
    public LoanLibraryItemCommand(LibraryItemService libraryItemService, int itemId, int memberId) {
        this.libraryItemService = libraryItemService;
        this.itemId = itemId;
        this.memberId = memberId;
    }

    /**
     * Executes the command to loan a library item to a member in the library system.
     */
    @Override
    public void execute() {
        libraryItemService.loanItem(itemId, memberId);
    }
}
