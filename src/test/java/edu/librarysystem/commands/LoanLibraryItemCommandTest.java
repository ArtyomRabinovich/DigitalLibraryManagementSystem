package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoanLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private LoanLibraryItemCommand command;
    private static final int ITEM_ID = 1;
    private static final int MEMBER_ID = 1;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new LoanLibraryItemCommand(libraryItemService, ITEM_ID, MEMBER_ID);
    }

    @Test
    public void testExecute() {
        command.execute();
        verify(libraryItemService, times(1)).loanItem(ITEM_ID, MEMBER_ID);
    }
}
