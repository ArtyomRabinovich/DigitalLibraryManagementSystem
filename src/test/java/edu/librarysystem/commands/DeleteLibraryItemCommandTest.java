package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code DeleteLibraryItemCommand}.
 */
public class DeleteLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private DeleteLibraryItemCommand command;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new DeleteLibraryItemCommand(libraryItemService, 1);
    }

    /**
     * Tests the {@code execute} method of {@code DeleteLibraryItemCommand}.
     * Verifies that the {@code deleteItem} method of {@code LibraryItemService} is called with the correct parameter.
     */
    @Test
    public void testExecute() {
        command.execute();
        Mockito.verify(libraryItemService).deleteItem(1);
    }
}
