package edu.librarysystem.models;

import edu.librarysystem.interfaces.LibraryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;
    private static final String TITLE = "Effective Java";
    private static final String AUTHOR = "Joshua Bloch";
    private static final int PAGES = 416;
    private static final String ISBN = "978-0134685991";
    private static final int YEAR_PUBLISHED = 2017;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field lastIdField = Book.class.getDeclaredField("lastId");
        lastIdField.setAccessible(true);
        lastIdField.setInt(null, 0);

        book = new Book(TITLE, AUTHOR, PAGES, ISBN, YEAR_PUBLISHED);
    }

    @Test
    public void testGetId() {
        int id = book.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetTitle() {
        assertEquals(TITLE, book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals(AUTHOR, book.getAuthor());
    }

    @Test
    public void testGetPages() {
        assertEquals(PAGES, book.getPages());
    }

    @Test
    public void testIsAvailable() {
        assertTrue(book.isAvailable());
    }

    @Test
    public void testSetAvailable() {
        book.setAvailable(false);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testGetLoanedTo() {
        assertNull(book.getLoanedTo());
    }

    @Test
    public void testSetLoanedTo() {
        Member member = Mockito.mock(Member.class);
        book.setLoanedTo(member);
        assertEquals(member, book.getLoanedTo());
    }

    @Test
    public void testClone() {
        Book clonedBook = book.clone();
        assertNotSame(book, clonedBook);
        assertEquals(book.getTitle(), clonedBook.getTitle());
        assertEquals(book.getAuthor(), clonedBook.getAuthor());
        assertEquals(book.getPages(), clonedBook.getPages());
        assertEquals(book.getIsbn(), clonedBook.getIsbn());
        assertEquals(book.getYearPublished(), clonedBook.getYearPublished());
    }

    @Test
    public void testGetIsbn() {
        assertEquals(ISBN, book.getIsbn());
    }

    @Test
    public void testGetYearPublished() {
        assertEquals(YEAR_PUBLISHED, book.getYearPublished());
    }
}
