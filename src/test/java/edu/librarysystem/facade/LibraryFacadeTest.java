package edu.librarysystem.facade;

import edu.librarysystem.commands.*;
import edu.librarysystem.interfaces.Command;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;
import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * Test class for {@code LibraryFacade}.
 */
public class LibraryFacadeTest {

    private LibrarySystem librarySystem;
    private LibraryItemService libraryItemService;
    private UserService userService;
    private LibraryFacade libraryFacade;

    @BeforeEach
    public void setUp() {
        librarySystem = Mockito.mock(LibrarySystem.class);
        libraryItemService = Mockito.mock(LibraryItemService.class);
        userService = Mockito.mock(UserService.class);
        libraryFacade = new LibraryFacade(librarySystem, libraryItemService, userService);
    }

    /**
     * Tests the {@code addBook} method of {@code LibraryFacade}.
     * Verifies that the {@code AddLibraryItemCommand} is created and executed with the correct parameters.
     */
    @Test
    public void testAddBook() {
        libraryFacade.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(libraryItemService).addItem("Test Title", "Test Author", 123, "1234567890", 2021);
    }

    /**
     * Tests the {@code deleteBook} method of {@code LibraryFacade}.
     * Verifies that the {@code DeleteLibraryItemCommand} is created and executed with the correct parameter.
     */
    @Test
    public void testDeleteBook() {
        libraryFacade.deleteBook(1);
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(libraryItemService).deleteItem(1);
    }

    /**
     * Tests the {@code loanBook} method of {@code LibraryFacade}.
     * Verifies that the {@code LoanLibraryItemCommand} is created and executed with the correct parameters.
     */
    @Test
    public void testLoanBook() {
        libraryFacade.loanBook(1, 1);
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(libraryItemService).loanItem(1, 1);
    }

    /**
     * Tests the {@code returnBook} method of {@code LibraryFacade}.
     * Verifies that the {@code ReturnLibraryItemCommand} is created and executed with the correct parameter.
     */
    @Test
    public void testReturnBook() {
        libraryFacade.returnBook(1);
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(libraryItemService).returnItem(1);
    }

    /**
     * Tests the {@code addMember} method of {@code LibraryFacade}.
     * Verifies that the {@code AddMemberCommand} is created and executed with the correct parameter.
     */
    @Test
    public void testAddMember() {
        libraryFacade.addMember("Test Member");
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(userService).addMember("Test Member");
    }

    /**
     * Tests the {@code deleteMember} method of {@code LibraryFacade}.
     * Verifies that the {@code DeleteMemberCommand} is created and executed with the correct parameter.
     */
    @Test
    public void testDeleteMember() {
        libraryFacade.deleteMember(1);
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(userService).deleteMember(1);
    }

    /**
     * Tests the {@code duplicateBook} method of {@code LibraryFacade}.
     * Verifies that the {@code DuplicateBookCommand} is created and executed with the correct parameter.
     */
    @Test
    public void testDuplicateBook() {
        // Mock the behavior of getItem to return a Book instance
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        Mockito.when(libraryItemService.getItem(1)).thenReturn(book);

        libraryFacade.duplicateBook(1);
        ArgumentCaptor<Command> captor = ArgumentCaptor.forClass(Command.class);
        Mockito.verify(librarySystem).addCommand(captor.capture());
        Command command = captor.getValue();
        command.execute();
        Mockito.verify(libraryItemService).cloneBook(1);
    }
}
