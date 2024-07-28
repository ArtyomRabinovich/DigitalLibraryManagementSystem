package edu.librarysystem.commands;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class AddLibraryItemCommandTest {
    private LibraryItemService libraryItemService;
    private LibraryItem item;
    private AddLibraryItemCommand addLibraryItemCommand;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        item = Mockito.mock(LibraryItem.class);
        addLibraryItemCommand = new AddLibraryItemCommand(libraryItemService, item);
    }

    @Test
    public void testExecute() {
        addLibraryItemCommand.execute();
        verify(libraryItemService, times(1)).addItem(item);
    }

    @Test
    public void testExecuteSynchronization() {
        synchronized (libraryItemService) {
            addLibraryItemCommand.execute();
            verify(libraryItemService, times(1)).addItem(item);
        }
    }
}
