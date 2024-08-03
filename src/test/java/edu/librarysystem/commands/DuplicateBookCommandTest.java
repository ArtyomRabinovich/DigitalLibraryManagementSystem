package edu.librarysystem.commands;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.services.LibraryItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for {@code DuplicateBookCommand}.
 */
public class DuplicateBookCommandTest {

    private LibraryItemService libraryItemService;
    private DuplicateBookCommand command;
    private Book book;

    @BeforeEach
    public void setUp() {
        libraryItemService = Mockito.mock(LibraryItemService.class);
        book = Mockito.mock(Book.class);
        command = new DuplicateBookCommand(libraryItemService, 1);
    }

    /**
     * Tests the {@code execute} method of {@code DuplicateBookCommand}.
     * Verifies that the {@code cloneBook} method of {@code LibraryItemService} is called
     * if the item with the given ID is a book.
     */
    @Test
    public void testExecuteWhenItemIsBook() {
        Mockito.when(libraryItemService.getItem(1)).thenReturn(book);
        command.execute();
        Mockito.verify(libraryItemService).cloneBook(1);
    }

    /**
     * Tests the {@code execute} method of {@code DuplicateBookCommand}.
     * Verifies that the {@code cloneBook} method of {@code LibraryItemService} is not called
     * if the item with the given ID is not a book.
     */
    @Test
    public void testExecuteWhenItemIsNotBook() {
        LibraryItem nonBookItem = Mockito.mock(LibraryItem.class);
        Mockito.when(libraryItemService.getItem(1)).thenReturn(nonBookItem);
        command.execute();
        Mockito.verify(libraryItemService, Mockito.never()).cloneBook(1);
    }

    /**
     * Tests the {@code execute} method of {@code DuplicateBookCommand}.
     * Verifies that the {@code cloneBook} method of {@code LibraryItemService} is not called
     * if the item with the given ID does not exist.
     */
    @Test
    public void testExecuteWhenItemDoesNotExist() {
        Mockito.when(libraryItemService.getItem(1)).thenReturn(null);
        command.execute();
        Mockito.verify(libraryItemService, Mockito.never()).cloneBook(1);
    }
}
