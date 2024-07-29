package edu.librarysystem.commands;

import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddLibraryItemCommandTest {

    private LibraryItemService libraryItemService;
    private AddLibraryItemCommand command;
    private static final String TITLE = "Clean Code";
    private static final String AUTHOR = "Robert C. Martin";
    private static final int PAGES = 464;
    private static final String ISBN = "978-0132350884";
    private static final int YEAR_PUBLISHED = 2008;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        command = new AddLibraryItemCommand(libraryItemService, TITLE, AUTHOR, PAGES, ISBN, YEAR_PUBLISHED);
    }

    @Test
    public void testExecute() {
        command.execute();
        verify(libraryItemService, times(1)).addItem(TITLE, AUTHOR, PAGES, ISBN, YEAR_PUBLISHED);
    }
}
