package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class DeleteLibraryItemCommandTest {
    private LibraryItemService libraryItemService;
    private int itemId;
    private DeleteLibraryItemCommand deleteLibraryItemCommand;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        itemId = 123; // example itemId
        deleteLibraryItemCommand = new DeleteLibraryItemCommand(libraryItemService, itemId);
    }

    @Test
    public void testExecute() {
        deleteLibraryItemCommand.execute();
        verify(libraryItemService, times(1)).deleteItem(itemId);
    }

    @Test
    public void testExecuteSynchronization() {
        synchronized (libraryItemService) {
            deleteLibraryItemCommand.execute();
            verify(libraryItemService, times(1)).deleteItem(itemId);
        }
    }
}
