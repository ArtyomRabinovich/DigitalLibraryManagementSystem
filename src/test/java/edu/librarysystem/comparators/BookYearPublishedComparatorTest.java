package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookYearPublishedComparatorTest {
    private BookYearPublishedComparator comparator;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        comparator = new BookYearPublishedComparator();
        book1 = new Book("Effective Java", "Joshua Bloch", 416, "0134685997", 2018);
        book2 = new Book("Clean Code", "Robert C. Martin", 464, "0132350882", 2008);
        book3 = new Book("The Pragmatic Programmer", "Andrew Hunt", 320, "020161622X", 1999);
    }

    @Test
    public void testCompareEqualYears() {
        Book book4 = new Book("Some Book", "Some Author", 384, "1234567890", 2018);
        assertEquals(0, comparator.compare(book1, book4));
    }

    @Test
    public void testCompareDifferentYears() {
        assertTrue(comparator.compare(book1, book2) > 0); // 2018 is after 2008
        assertTrue(comparator.compare(book2, book1) < 0); // 2008 is before 2018
        assertTrue(comparator.compare(book1, book3) > 0); // 2018 is after 1999
        assertTrue(comparator.compare(book3, book1) < 0); // 1999 is before 2018
        assertTrue(comparator.compare(book2, book3) > 0); // 2008 is after 1999
        assertTrue(comparator.compare(book3, book2) < 0); // 1999 is before 2008
    }
}
