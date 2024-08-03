package edu.librarysystem.factories;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;

/**
 * The {@code Factory} class provides methods to create instances of {@code Book} and {@code Member}.
 */
public class Factory {

    /**
     * Creates a new {@code Book} with the specified parameters.
     *
     * @param title         the title of the book
     * @param author        the author of the book
     * @param pages         the number of pages in the book
     * @param isbn          the ISBN of the book
     * @param yearPublished the year the book was published
     * @return a new {@code Book} instance
     */
    public Book createBook(String title, String author, int pages, String isbn, int yearPublished) {
        return new Book(title, author, pages, isbn, yearPublished);
    }

    /**
     * Creates a new {@code Member} with the specified name.
     *
     * @param name the name of the member
     * @return a new {@code Member} instance
     */
    public Member createMember(String name) {
        return new Member(name);
    }
}
