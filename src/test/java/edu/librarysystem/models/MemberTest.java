package edu.librarysystem.models;

import edu.librarysystem.interfaces.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private Member member;
    private static final String NAME = "John Doe";

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Reset the lastId to 0 using reflection
        Field lastIdField = Member.class.getDeclaredField("lastId");
        lastIdField.setAccessible(true);
        lastIdField.setInt(null, 0);

        member = new Member(NAME);
    }

    @Test
    public void testGetId() {
        int id = member.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetName() {
        assertEquals(NAME, member.getName());
    }

    @Test
    public void testGetCreationDate() {
        Date creationDate = member.getCreationDate();
        assertNotNull(creationDate);
        assertTrue(new Date().getTime() - creationDate.getTime() < 1000);
    }

    @Test
    public void testGetLoanedBooks() {
        List<Book> loanedBooks = member.getLoanedBooks();
        assertNotNull(loanedBooks);
        assertTrue(loanedBooks.isEmpty());
    }

    @Test
    public void testAddLoanedBook() {
        Book book = Mockito.mock(Book.class);
        member.addLoanedBook(book);
        assertEquals(1, member.getLoanedBooks().size());
        assertEquals(book, member.getLoanedBooks().get(0));
        assertEquals(1, member.getTotalBooksLoaned());
        assertEquals(1, member.getCurrentlyLoanedBooks());
    }

    @Test
    public void testRemoveLoanedBook() {
        Book book = Mockito.mock(Book.class);
        member.addLoanedBook(book);
        member.removeLoanedBook(book);
        assertTrue(member.getLoanedBooks().isEmpty());
        assertEquals(1, member.getTotalBooksLoaned());
        assertEquals(0, member.getCurrentlyLoanedBooks());
    }

    @Test
    public void testGetTotalBooksLoaned() {
        Book book1 = Mockito.mock(Book.class);
        Book book2 = Mockito.mock(Book.class);
        member.addLoanedBook(book1);
        member.addLoanedBook(book2);
        member.removeLoanedBook(book1);
        assertEquals(2, member.getTotalBooksLoaned());
    }

    @Test
    public void testGetCurrentlyLoanedBooks() {
        Book book1 = Mockito.mock(Book.class);
        Book book2 = Mockito.mock(Book.class);
        member.addLoanedBook(book1);
        member.addLoanedBook(book2);
        assertEquals(2, member.getCurrentlyLoanedBooks());
        member.removeLoanedBook(book1);
        assertEquals(1, member.getCurrentlyLoanedBooks());
    }
}
