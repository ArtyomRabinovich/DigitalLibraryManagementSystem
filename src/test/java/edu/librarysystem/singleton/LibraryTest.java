package edu.librarysystem.singleton;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.observers.LibraryChangeListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the Library singleton.
 */
public class LibraryTest {

    private Library library;
    private LibraryChangeListener listener;

    /**
     * Sets up the test environment before each test.
     * Resets the Library singleton and initializes a new instance with a mock listener.
     */
    @BeforeEach
    public void setUp() {
        resetLibrarySingleton();
        library = Library.getInstance();
        listener = Mockito.mock(LibraryChangeListener.class);
        library.addChangeListener(listener);
    }

    /**
     * Resets the Library singleton instance for testing purposes.
     */
    private void resetLibrarySingleton() {
        Library.resetInstance();
    }

    /**
     * Tests that the Library singleton instance is the same across multiple calls.
     */
    @Test
    public void testSingletonInstance() {
        Library anotherInstance = Library.getInstance();
        assertSame(library, anotherInstance);
    }

    /**
     * Tests adding a book to the library.
     * Verifies that the book is added correctly and that the change listener is notified.
     */
    @Test
    public void testAddBook() {
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        List<Book> books = library.getAllBooks();
        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(123, book.getPages());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(2021, book.getYearPublished());
        Mockito.verify(listener).onLibraryChanged();
    }

    /**
     * Tests deleting a book from the library.
     * Verifies that the book is deleted correctly and that the change listener is notified.
     */
    @Test
    public void testDeleteBook() {
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        Book book = library.getAllBooks().get(0);
        assertTrue(library.deleteBook(book.getId()));
        assertTrue(library.getAllBooks().isEmpty());
        Mockito.verify(listener, Mockito.times(2)).onLibraryChanged();
    }

    /**
     * Tests that a book cannot be deleted if it is currently loaned out.
     * Verifies that the deletion fails and the book remains in the library.
     */
    @Test
    public void testDeleteBookWhenLoaned() {
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        Book book = library.getAllBooks().get(0);
        library.addMember("Test Member");
        Member member = library.getAllMembers().get(0);
        library.loanBook(book.getId(), member.getId());
        assertFalse(library.deleteBook(book.getId()));
        assertFalse(library.getAllBooks().isEmpty());
    }

    /**
     * Tests adding a member to the library.
     * Verifies that the member is added correctly and that the change listener is notified.
     */
    @Test
    public void testAddMember() {
        library.addMember("Test Member");
        List<Member> members = library.getAllMembers();
        assertEquals(1, members.size());
        Member member = members.get(0);
        assertEquals("Test Member", member.getName());
        Mockito.verify(listener).onLibraryChanged();
    }

    /**
     * Tests deleting a member from the library.
     * Verifies that the member is deleted correctly and that the change listener is notified.
     */
    @Test
    public void testDeleteMember() {
        library.addMember("Test Member");
        Member member = library.getAllMembers().get(0);
        assertTrue(library.deleteMember(member.getId()));
        assertTrue(library.getAllMembers().isEmpty());
        Mockito.verify(listener, Mockito.times(2)).onLibraryChanged();
    }

    /**
     * Tests that a member cannot be deleted if they currently have loaned books.
     * Verifies that the deletion fails and the member remains in the library.
     */
    @Test
    public void testDeleteMemberWithLoanedBooks() {
        library.addMember("Test Member");
        Member member = library.getAllMembers().get(0);
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        Book book = library.getAllBooks().get(0);
        library.loanBook(book.getId(), member.getId());
        assertFalse(library.deleteMember(member.getId()));
        assertFalse(library.getAllMembers().isEmpty());
    }

    /**
     * Tests loaning a book to a member.
     * Verifies that the book's availability status is updated, the member's loaned books list is updated,
     * and the change listener is notified.
     */
    @Test
    public void testLoanBook() {
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        Book book = library.getAllBooks().get(0);
        library.addMember("Test Member");
        Member member = library.getAllMembers().get(0);
        library.loanBook(book.getId(), member.getId());
        assertFalse(book.isAvailable());
        assertEquals(member, book.getLoanedTo());
        assertTrue(member.getLoanedBooks().contains(book));
        Mockito.verify(listener, Mockito.times(3)).onLibraryChanged();
    }

    /**
     * Tests returning a loaned book.
     * Verifies that the book's availability status is updated, the member's loaned books list is updated,
     * and the change listener is notified.
     */
    @Test
    public void testReturnBook() {
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        Book book = library.getAllBooks().get(0);
        library.addMember("Test Member");
        Member member = library.getAllMembers().get(0);
        library.loanBook(book.getId(), member.getId());
        library.returnBook(book.getId());
        assertTrue(book.isAvailable());
        assertNull(book.getLoanedTo());
        assertFalse(member.getLoanedBooks().contains(book));
        Mockito.verify(listener, Mockito.times(4)).onLibraryChanged();
    }

    /**
     * Tests cloning a book in the library.
     * Verifies that a new book with the same details but a unique ID is created, and that the change listener
     * is notified.
     */
    @Test
    public void testCloneBook() {
        // Add a book to the library
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        // Verify that one book is added
        assertEquals(1, library.getAllBooks().size());

        // Clone the book
        Book originalBook = library.getAllBooks().get(0);
        library.cloneBook(originalBook.getId());

        // Verify that there are now two books in the library
        assertEquals(2, library.getAllBooks().size());

        // Verify that the cloned book has a new ID and is otherwise identical to the original
        Book clonedBook = library.getAllBooks().stream()
                .filter(book -> book.getId() != originalBook.getId())
                .findFirst().orElse(null);

        assertNotNull(clonedBook);
        assertEquals(originalBook.getTitle(), clonedBook.getTitle());
        assertEquals(originalBook.getAuthor(), clonedBook.getAuthor());
        assertEquals(originalBook.getPages(), clonedBook.getPages());
        assertEquals(originalBook.getIsbn(), clonedBook.getIsbn());
        assertEquals(originalBook.getYearPublished(), clonedBook.getYearPublished());

        // Verify that the library change listener was notified twice (once for addBook, once for cloneBook)
        Mockito.verify(listener, Mockito.times(2)).onLibraryChanged();
    }

    /**
     * Tests that a book cannot be cloned if it is currently loaned out.
     * Verifies that the cloning fails and the book remains the only copy in the library.
     */
    @Test
    public void testCloneBookWhenLoaned() {
        library.addBook("Test Title", "Test Author", 123, "1234567890", 2021);
        assertEquals(1, library.getAllBooks().size());

        Book originalBook = library.getAllBooks().getFirst();
        library.addMember("Test Member");
        Member member = library.getAllMembers().getFirst();
        library.loanBook(originalBook.getId(), member.getId());

        library.cloneBook(originalBook.getId());

        assertEquals(1, library.getAllBooks().size());

        Mockito.verify(listener, Mockito.times(3)).onLibraryChanged();
    }
}
