package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookAuthorComparatorTest {

    private BookAuthorComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new BookAuthorComparator();
    }

    @Test
    public void testCompare() {
        Book book1 = new Book("Title1", "AuthorA", 200, "ISBN1", 2020);
        Book book2 = new Book("Title2", "AuthorB", 300, "ISBN2", 2021);

        int result = comparator.compare(book1, book2);
        assertTrue(result < 0, "AuthorA should be less than AuthorB");

        result = comparator.compare(book2, book1);
        assertTrue(result > 0, "AuthorB should be greater than AuthorA");

        Book book3 = new Book("Title3", "AuthorA", 150, "ISBN3", 2019);
        result = comparator.compare(book1, book3);
        assertEquals(0, result, "Authors should be equal");
    }
}
