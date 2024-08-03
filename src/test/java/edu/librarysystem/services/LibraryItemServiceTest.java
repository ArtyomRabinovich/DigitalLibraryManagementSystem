package edu.librarysystem.services;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.singleton.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

/**
 * Test class for {@code LibraryItemService}.
 */
public class LibraryItemServiceTest {

    private Library library;
    private LibraryItemService service;

    @BeforeEach
    public void setUp() throws Exception {
        library = Mockito.mock(Library.class);
        service = new LibraryItemService();

        // Use reflection to inject the mocked library into the service
        Field libraryField = LibraryItemService.class.getDeclaredField("library");
        libraryField.setAccessible(true);
        libraryField.set(service, library);
    }

    /**
     * Tests the {@code addItem} method of {@code LibraryItemService}.
     * Verifies that the {@code addBook} method of {@code Library} is called with the correct parameters.
     */
    @Test
    public void testAddItem() {
        service.addItem("Test Title", "Test Author", 123, "1234567890", 2021);
        Mockito.verify(library).addBook("Test Title", "Test Author", 123, "1234567890", 2021);
    }

    /**
     * Tests the {@code deleteItem} method of {@code LibraryItemService}.
     * Verifies that the {@code deleteBook} method of {@code Library} is called with the correct parameter.
     */
    @Test
    public void testDeleteItem() {
        service.deleteItem(1);
        Mockito.verify(library).deleteBook(1);
    }

    /**
     * Tests the {@code loanItem} method of {@code LibraryItemService}.
     * Verifies that the {@code loanBook} method of {@code Library} is called with the correct parameters.
     */
    @Test
    public void testLoanItem() {
        service.loanItem(1, 1);
        Mockito.verify(library).loanBook(1, 1);
    }

    /**
     * Tests the {@code returnItem} method of {@code LibraryItemService}.
     * Verifies that the {@code returnBook} method of {@code Library} is called with the correct parameter.
     */
    @Test
    public void testReturnItem() {
        service.returnItem(1);
        Mockito.verify(library).returnBook(1);
    }

    /**
     * Tests the {@code getItem} method of {@code LibraryItemService}.
     * Verifies that the {@code getBook} method of {@code Library} is called with the correct parameter.
     * Also verifies that the returned item is the expected one.
     */
    @Test
    public void testGetItem() {
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        Mockito.when(library.getBook(1)).thenReturn(book);
        LibraryItem item = service.getItem(1);
        Mockito.verify(library).getBook(1);
        assert item.equals(book);
    }

    /**
     * Tests the {@code cloneBook} method of {@code LibraryItemService}.
     * Verifies that the {@code cloneBook} method of {@code Library} is called with the correct parameter.
     */
    @Test
    public void testCloneBook() {
        service.cloneBook(1);
        Mockito.verify(library).cloneBook(1);
    }
}
