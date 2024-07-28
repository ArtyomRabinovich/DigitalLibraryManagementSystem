package edu.librarysystem.comparators;

import edu.librarysystem.models.Book;
import java.util.Comparator;

public class BookYearPublishedComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getYearPublished(), book2.getYearPublished());
    }
}
