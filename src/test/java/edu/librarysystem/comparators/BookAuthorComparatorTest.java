package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookAuthorComparatorTest {
    private BookAuthorComparator comparator;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        comparator = new BookAuthorComparator();
        book1 = new Book("Effective Java", "Joshua Bloch", 416, "0134685997", 2018);
        book2 = new Book("Clean Code", "Robert C. Martin", 464, "0132350882", 2008);
        book3 = new Book("The Pragmatic Programmer", "Andrew Hunt", 320, "020161622X", 1999);
    }

    @Test
    public void testCompareEqualAuthors() {
        Book book4 = new Book("Java Puzzlers", "Joshua Bloch", 384, "032133678X", 2005);
        assertEquals(0, comparator.compare(book1, book4));
    }

    @Test
    public void testCompareDifferentAuthors() {
        assertTrue(comparator.compare(book1, book2) < 0);
        assertTrue(comparator.compare(book2, book1) > 0);
        assertTrue(comparator.compare(book1, book3) > 0);
        assertTrue(comparator.compare(book3, book1) < 0);
        assertTrue(comparator.compare(book2, book3) > 0);
        assertTrue(comparator.compare(book3, book2) < 0);
    }
}
