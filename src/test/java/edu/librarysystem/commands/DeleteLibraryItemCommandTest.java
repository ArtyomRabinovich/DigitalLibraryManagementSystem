package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private DeleteLibraryItemCommand command;
    private static final int ITEM_ID = 1;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new DeleteLibraryItemCommand(libraryItemService, ITEM_ID);
    }

    @Test
    public void testExecute() {
        command.execute();
        verify(libraryItemService, times(1)).deleteItem(ITEM_ID);
    }
}
