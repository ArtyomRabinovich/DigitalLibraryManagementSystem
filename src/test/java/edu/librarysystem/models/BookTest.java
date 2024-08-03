package edu.librarysystem.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@code Book}.
 */
public class BookTest {

    /**
     * Tests the constructor and getters of the {@code Book} class.
     * Verifies that a {@code Book} instance is created with the correct parameters.
     */
    @Test
    public void testBookConstructorAndGetters() {
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(123, book.getPages());
        assertEquals("1234567890", book.getIsbn());
        assertEquals(2021, book.getYearPublished());
        assertTrue(book.isAvailable());
        assertNull(book.getLoanedTo());
    }

    /**
     * Tests the {@code setAvailable} method of the {@code Book} class.
     * Verifies that the availability status of the book can be changed.
     */
    @Test
    public void testSetAvailable() {
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        book.setAvailable(false);
        assertFalse(book.isAvailable());
        book.setAvailable(true);
        assertTrue(book.isAvailable());
    }

    /**
     * Tests the {@code setLoanedTo} method of the {@code Book} class.
     * Verifies that the loanedTo property of the book can be set and retrieved.
     */
    @Test
    public void testSetLoanedTo() {
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        Member member = new Member("Test Member");
        book.setLoanedTo(member);
        assertEquals(member, book.getLoanedTo());
    }

    /**
     * Tests the {@code clone} method of the {@code Book} class.
     * Verifies that a clone of the book is created with a new unique ID.
     */
    @Test
    public void testClone() {
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        Book clonedBook = book.clone();
        assertNotEquals(book.getId(), clonedBook.getId());
        assertEquals(book.getTitle(), clonedBook.getTitle());
        assertEquals(book.getAuthor(), clonedBook.getAuthor());
        assertEquals(book.getPages(), clonedBook.getPages());
        assertEquals(book.getIsbn(), clonedBook.getIsbn());
        assertEquals(book.getYearPublished(), clonedBook.getYearPublished());
        assertTrue(clonedBook.isAvailable());
        assertNull(clonedBook.getLoanedTo());
    }
}
