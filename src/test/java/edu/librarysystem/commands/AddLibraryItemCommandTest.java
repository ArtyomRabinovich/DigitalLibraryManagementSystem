package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code AddLibraryItemCommand}.
 */
public class AddLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private AddLibraryItemCommand command;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new AddLibraryItemCommand(libraryItemService, "Test Title", "Test Author", 123, "1234567890", 2021);
    }

    /**
     * Tests the {@code execute} method of {@code AddLibraryItemCommand}.
     * Verifies that the {@code addItem} method of {@code LibraryItemService} is called with the correct parameters.
     */
    @Test
    public void testExecute() {
        command.execute();
        Mockito.verify(libraryItemService).addItem("Test Title", "Test Author", 123, "1234567890", 2021);
    }
}
