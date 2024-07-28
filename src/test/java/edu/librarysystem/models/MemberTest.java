package edu.librarysystem.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class MemberTest {

    @Test
    void testMemberCreation() {
        Member member = new Member("Jane Doe");

        assertEquals("Jane Doe", member.getName());
        assertNotNull(member.getCreationDate());
        assertEquals(0, member.getTotalBooksLoaned());
        assertEquals(0, member.getCurrentlyLoanedBooks());
        assertTrue(member.getLoanedBooks().isEmpty());
    }

    @Test
    void testAddLoanedBook() {
        Member member = new Member("Jane Doe");
        Book book = new Book("Effective Java", "Joshua Bloch", 412, "978-0134685991", 2018);

        member.addLoanedBook(book);

        assertEquals(1, member.getCurrentlyLoanedBooks());
        assertEquals(1, member.getTotalBooksLoaned());
        assertTrue(member.getLoanedBooks().contains(book));
    }

    @Test
    void testRemoveLoanedBook() {
        Member member = new Member("Jane Doe");
        Book book = new Book("Effective Java", "Joshua Bloch", 412, "978-0134685991", 2018);

        member.addLoanedBook(book);
        member.removeLoanedBook(book);

        assertEquals(0, member.getCurrentlyLoanedBooks());
        assertEquals(1, member.getTotalBooksLoaned());
        assertFalse(member.getLoanedBooks().contains(book));
    }
}
