package edu.librarysystem.singleton;

import edu.librarysystem.facade.LibraryFacade;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LibrarianTest {
    private Librarian librarian;
    private LibraryFacade libraryFacadeMock;

    @BeforeEach
    public void setUp() {
        librarian = Librarian.getInstance();

        // Mock dependencies
        LibraryItemService libraryItemServiceMock = mock(LibraryItemService.class);
        UserService userServiceMock = mock(UserService.class);
        LibrarySystem librarySystemMock = mock(LibrarySystem.class);
        libraryFacadeMock = mock(LibraryFacade.class);

        try {
            // Access the private field 'libraryFacade' and replace it with the mock
            Field libraryFacadeField = Librarian.class.getDeclaredField("libraryFacade");
            libraryFacadeField.setAccessible(true);
            libraryFacadeField.set(librarian, libraryFacadeMock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddMember() {
        librarian.addMember("John Doe");
        verify(libraryFacadeMock).addMember("John Doe");
    }

    @Test
    public void testAddBook() {
        librarian.addBook("Title", "Author", 300, "1234567890", 2020);
        verify(libraryFacadeMock).addBook("Title", "Author", 300, "1234567890", 2020);
    }

    @Test
    public void testLoanBook() {
        librarian.loanBook(1, 1);
        verify(libraryFacadeMock).loanBook(1, 1);
    }

    @Test
    public void testReturnBook() {
        librarian.returnBook(1);
        verify(libraryFacadeMock).returnBook(1);
    }

    @Test
    public void testDeleteBook() {
        when(libraryFacadeMock.deleteBook(1)).thenReturn(true);
        boolean result = librarian.deleteBook(1);
        verify(libraryFacadeMock).deleteBook(1);
        assertTrue(result);
    }

    @Test
    public void testDeleteMember() {
        when(libraryFacadeMock.deleteMember(1)).thenReturn(true);
        boolean result = librarian.deleteMember(1);
        verify(libraryFacadeMock).deleteMember(1);
        assertTrue(result);
    }

    @Test
    public void testDuplicateBook() {
        librarian.duplicateBook(1);
        verify(libraryFacadeMock).duplicateBook(1);
    }
}
