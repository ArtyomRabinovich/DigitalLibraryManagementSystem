package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookPagesComparatorTest {
    private BookPagesComparator comparator;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        comparator = new BookPagesComparator();
        book1 = new Book("Effective Java", "Joshua Bloch", 416, "0134685997", 2018);
        book2 = new Book("Clean Code", "Robert C. Martin", 464, "0132350882", 2008);
        book3 = new Book("The Pragmatic Programmer", "Andrew Hunt", 320, "020161622X", 1999);
    }

    @Test
    public void testCompareEqualPages() {
        Book book4 = new Book("Some Book", "Some Author", 416, "1234567890", 2020);
        assertEquals(0, comparator.compare(book1, book4));
    }

    @Test
    public void testCompareDifferentPages() {
        assertTrue(comparator.compare(book1, book2) < 0); // book1 has fewer pages than book2
        assertTrue(comparator.compare(book2, book1) > 0); // book2 has more pages than book1
        assertTrue(comparator.compare(book1, book3) > 0); // book1 has more pages than book3
        assertTrue(comparator.compare(book3, book1) < 0); // book3 has fewer pages than book1
        assertTrue(comparator.compare(book2, book3) > 0); // book2 has more pages than book3
        assertTrue(comparator.compare(book3, book2) < 0); // book3 has fewer pages than book2
    }
}
