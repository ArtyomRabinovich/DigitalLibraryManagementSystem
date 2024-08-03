package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code ReturnLibraryItemCommand}.
 */
public class ReturnLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private ReturnLibraryItemCommand command;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new ReturnLibraryItemCommand(libraryItemService, 1);
    }

    /**
     * Tests the {@code execute} method of {@code ReturnLibraryItemCommand}.
     * Verifies that the {@code returnItem} method of {@code LibraryItemService} is called
     * with the correct parameter.
     */
    @Test
    public void testExecute() {
        command.execute();
        Mockito.verify(libraryItemService).returnItem(1);
    }
}
