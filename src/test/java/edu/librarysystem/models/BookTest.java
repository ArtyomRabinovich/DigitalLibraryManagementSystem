package edu.librarysystem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookCreation() {
        Book book = new Book("Effective Java", "Joshua Bloch", 412, "978-0134685991", 2018);

        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals(412, book.getPages());
        assertTrue(book.isAvailable());
        assertEquals("978-0134685991", book.getIsbn());
        assertEquals(2018, book.getYearPublished());
        assertNull(book.getLoanedTo());
    }

    @Test
    void testSetLoanedTo() {
        Book book = new Book("Effective Java", "Joshua Bloch", 412, "978-0134685991", 2018);
        Member member = new Member("John Doe");

        book.setLoanedTo(member);

        assertEquals(member, book.getLoanedTo());
    }

    @Test
    void testSetAvailable() {
        Book book = new Book("Effective Java", "Joshua Bloch", 412, "978-0134685991", 2018);

        book.setAvailable(false);

        assertFalse(book.isAvailable());
    }

    @Test
    void testClone() {
        Book book = new Book("Effective Java", "Joshua Bloch", 412, "978-0134685991", 2018);
        Book clonedBook = book.clone();

        assertNotSame(book, clonedBook);
        assertEquals(book.getTitle(), clonedBook.getTitle());
        assertEquals(book.getAuthor(), clonedBook.getAuthor());
        assertEquals(book.getPages(), clonedBook.getPages());
        assertEquals(book.getIsbn(), clonedBook.getIsbn());
        assertEquals(book.getYearPublished(), clonedBook.getYearPublished());
    }
}
