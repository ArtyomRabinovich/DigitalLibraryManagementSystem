package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import java.util.Comparator;

/**
 * The BookAuthorComparator class implements the Comparator interface and
 * provides a comparison function to compare two books by their authors' names.
 */
public class BookAuthorComparator implements Comparator<Book> {

    /**
     * Compares two books by their authors' names.
     *
     * @param book1 the first book to be compared.
     * @param book2 the second book to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Book book1, Book book2) {
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}
