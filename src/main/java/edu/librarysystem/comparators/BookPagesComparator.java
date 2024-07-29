package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import java.util.Comparator;

/**
 * The BookPagesComparator class implements the Comparator interface and
 * provides a comparison function to compare two books by their number of pages.
 */
public class BookPagesComparator implements Comparator<Book> {

    /**
     * Compares two books by their number of pages.
     *
     * @param book1 the first book to be compared.
     * @param book2 the second book to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument
     *         has less than, equal to, or greater than the number of pages of the second.
     */
    @Override
    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getPages(), book2.getPages());
    }
}
