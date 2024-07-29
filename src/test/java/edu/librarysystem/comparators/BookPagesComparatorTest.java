package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookPagesComparatorTest {

    private BookPagesComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new BookPagesComparator();
    }

    @Test
    public void testCompare() {
        Book book1 = new Book("Title1", "AuthorA", 200, "ISBN1", 2020);
        Book book2 = new Book("Title2", "AuthorB", 300, "ISBN2", 2021);

        int result = comparator.compare(book1, book2);
        assertTrue(result < 0, "Book with 200 pages should be less than book with 300 pages");

        result = comparator.compare(book2, book1);
        assertTrue(result > 0, "Book with 300 pages should be greater than book with 200 pages");

        Book book3 = new Book("Title3", "AuthorC", 200, "ISBN3", 2019);
        result = comparator.compare(book1, book3);
        assertEquals(0, result, "Books with the same number of pages should be equal");
    }
}
