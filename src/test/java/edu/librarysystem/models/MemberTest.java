package edu.librarysystem.models;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@code Member}.
 */
public class MemberTest {

    /**
     * Tests the constructor and getters of the {@code Member} class.
     * Verifies that a {@code Member} instance is created with the correct parameters.
     */
    @Test
    public void testMemberConstructorAndGetters() {
        Member member = new Member("Test Member");
        assertEquals("Test Member", member.getName());
        assertEquals(0, member.getTotalBooksLoaned());
        assertEquals(0, member.getCurrentlyLoanedBooks());
        assertNotNull(member.getCreationDate());
        assertTrue(member.getLoanedBooks().isEmpty());
    }

    /**
     * Tests the {@code addLoanedBook} method of the {@code Member} class.
     * Verifies that a book can be added to the member's loaned books list.
     */
    @Test
    public void testAddLoanedBook() {
        Member member = new Member("Test Member");
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        member.addLoanedBook(book);
        List<Book> loanedBooks = member.getLoanedBooks();
        assertTrue(loanedBooks.contains(book));
        assertEquals(1, member.getTotalBooksLoaned());
        assertEquals(1, member.getCurrentlyLoanedBooks());
    }

    /**
     * Tests the {@code removeLoanedBook} method of the {@code Member} class.
     * Verifies that a book can be removed from the member's loaned books list.
     */
    @Test
    public void testRemoveLoanedBook() {
        Member member = new Member("Test Member");
        Book book = new Book("Test Title", "Test Author", 123, "1234567890", 2021);
        member.addLoanedBook(book);
        member.removeLoanedBook(book);
        List<Book> loanedBooks = member.getLoanedBooks();
        assertFalse(loanedBooks.contains(book));
        assertEquals(1, member.getTotalBooksLoaned());
        assertEquals(0, member.getCurrentlyLoanedBooks());
    }

    /**
     * Tests the {@code getCreationDate} method of the {@code Member} class.
     * Verifies that the creation date is set correctly.
     */
    @Test
    public void testGetCreationDate() {
        Member member = new Member("Test Member");
        Date creationDate = member.getCreationDate();
        assertNotNull(creationDate);
        assertTrue(new Date().getTime() - creationDate.getTime() < 1000);  // Ensure the date is recent
    }
}
