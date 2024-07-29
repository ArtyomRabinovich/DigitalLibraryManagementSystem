package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookYearPublishedComparatorTest {

    private BookYearPublishedComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new BookYearPublishedComparator();
    }

    @Test
    public void testCompare() {
        Book book1 = new Book("Book1", "Author1", 100, "ISBN1", 2000);
        Book book2 = new Book("Book2", "Author2", 200, "ISBN2", 2010);

        int result = comparator.compare(book1, book2);
        assertTrue(result < 0, "Book published in 2000 should be less than book published in 2010");

        result = comparator.compare(book2, book1);
        assertTrue(result > 0, "Book published in 2010 should be greater than book published in 2000");

        Book book3 = new Book("Book3", "Author3", 150, "ISBN3", 2000);
        result = comparator.compare(book1, book3);
        assertEquals(0, result, "Books published in the same year should be equal");
    }
}
