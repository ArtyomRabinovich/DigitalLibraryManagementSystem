package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTitleComparatorTest {
    private BookTitleComparator comparator;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        comparator = new BookTitleComparator();
        book1 = new Book("Effective Java", "Joshua Bloch", 416, "0134685997", 2018);
        book2 = new Book("Clean Code", "Robert C. Martin", 464, "0132350882", 2008);
        book3 = new Book("The Pragmatic Programmer", "Andrew Hunt", 320, "020161622X", 1999);
    }

    @Test
    public void testCompareEqualTitles() {
        Book book4 = new Book("Effective Java", "Some Author", 384, "1234567890", 2020);
        assertEquals(0, comparator.compare(book1, book4));
    }

    @Test
    public void testCompareDifferentTitles() {
        assertTrue(comparator.compare(book1, book2) > 0); // "Effective Java" comes after "Clean Code"
        assertTrue(comparator.compare(book2, book1) < 0); // "Clean Code" comes before "Effective Java"
        assertTrue(comparator.compare(book1, book3) < 0); // "Effective Java" comes before "The Pragmatic Programmer"
        assertTrue(comparator.compare(book3, book1) > 0); // "The Pragmatic Programmer" comes after "Effective Java"
        assertTrue(comparator.compare(book2, book3) < 0); // "Clean Code" comes before "The Pragmatic Programmer"
        assertTrue(comparator.compare(book3, book2) > 0); // "The Pragmatic Programmer" comes after "Clean Code"
    }
}
