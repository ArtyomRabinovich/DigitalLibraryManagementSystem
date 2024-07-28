package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoanLibraryItemCommandTest {
    private LibraryItemService libraryItemService;
    private int itemId;
    private int memberId;
    private LoanLibraryItemCommand loanLibraryItemCommand;

    @BeforeEach
    public void setUp() {
        // Mocking LibraryItemService to isolate the test
        libraryItemService = Mockito.mock(LibraryItemService.class);

        // Using real integer values for itemId and memberId
        itemId = 789; // example itemId
        memberId = 101; // example memberId

        // Initializing the command with the mocked service and real itemId and memberId
        loanLibraryItemCommand = new LoanLibraryItemCommand(libraryItemService, itemId, memberId);
    }

    @Test
    public void testExecute() {
        // Executing the command
        loanLibraryItemCommand.execute();

        // Verifying that loanItem was called exactly once with the correct itemId and memberId
        verify(libraryItemService, times(1)).loanItem(itemId, memberId);
    }

    @Test
    public void testExecuteSynchronization() {
        // Ensuring the command executes within a synchronized block
        synchronized (libraryItemService) {
            loanLibraryItemCommand.execute();
            verify(libraryItemService, times(1)).loanItem(itemId, memberId);
        }
    }
}
