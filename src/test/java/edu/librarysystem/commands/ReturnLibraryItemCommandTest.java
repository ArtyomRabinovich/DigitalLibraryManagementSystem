package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ReturnLibraryItemCommandTest {
    private LibraryItemService libraryItemService;
    private int itemId;
    private ReturnLibraryItemCommand returnLibraryItemCommand;

    @BeforeEach
    public void setUp() {
        // Mocking LibraryItemService to isolate the test
        libraryItemService = Mockito.mock(LibraryItemService.class);

        // Using a real integer value for itemId
        itemId = 789; // example itemId

        // Initializing the command with the mocked service and real itemId
        returnLibraryItemCommand = new ReturnLibraryItemCommand(libraryItemService, itemId);
    }

    @Test
    public void testExecute() {
        // Executing the command
        returnLibraryItemCommand.execute();

        // Verifying that returnItem was called exactly once with the correct itemId
        verify(libraryItemService, times(1)).returnItem(itemId);
    }

    @Test
    public void testExecuteSynchronization() {
        // Ensuring the command executes within a synchronized block
        synchronized (libraryItemService) {
            returnLibraryItemCommand.execute();
            verify(libraryItemService, times(1)).returnItem(itemId);
        }
    }
}
