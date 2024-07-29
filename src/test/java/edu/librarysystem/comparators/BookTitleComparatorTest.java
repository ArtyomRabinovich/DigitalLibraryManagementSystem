package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTitleComparatorTest {

    private BookTitleComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new BookTitleComparator();
    }

    @Test
    public void testCompare() {
        Book book1 = new Book("A Tale of Two Cities", "Charles Dickens", 300, "ISBN1", 1859);
        Book book2 = new Book("War and Peace", "Leo Tolstoy", 1200, "ISBN2", 1869);

        int result = comparator.compare(book1, book2);
        assertTrue(result < 0, "Title 'A Tale of Two Cities' should be less than 'War and Peace'");

        result = comparator.compare(book2, book1);
        assertTrue(result > 0, "Title 'War and Peace' should be greater than 'A Tale of Two Cities'");

        Book book3 = new Book("A Tale of Two Cities", "Charles Dickens", 300, "ISBN3", 1859);
        result = comparator.compare(book1, book3);
        assertEquals(0, result, "Titles should be equal");
    }
}
