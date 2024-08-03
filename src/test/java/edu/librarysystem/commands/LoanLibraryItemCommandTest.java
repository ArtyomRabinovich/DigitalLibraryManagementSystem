package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code LoanLibraryItemCommand}.
 */
public class LoanLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private LoanLibraryItemCommand command;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new LoanLibraryItemCommand(libraryItemService, 1, 1);
    }

    /**
     * Tests the {@code execute} method of {@code LoanLibraryItemCommand}.
     * Verifies that the {@code loanItem} method of {@code LibraryItemService} is called
     * with the correct parameters.
     */
    @Test
    public void testExecute() {
        command.execute();
        Mockito.verify(libraryItemService).loanItem(1, 1);
    }
}
