package edu.librarysystem.interfaces;

import edu.librarysystem.models.Book;
import java.util.Date;
import java.util.List;

/**
 * The User interface provides a blueprint for user objects,
 * defining the essential methods that must be implemented by any class
 * representing a user in the library system.
 */
public interface User {

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return the user's unique ID.
     */
    int getId();

    /**
     * Retrieves the name of the user.
     *
     * @return the user's name.
     */
    String getName();

    /**
     * Retrieves the date the user account was created.
     *
     * @return the creation date of the user account.
     */
    Date getCreationDate();

    /**
     * Retrieves the list of books currently loaned to the user.
     *
     * @return a list of books currently loaned to the user.
     */
    List<Book> getLoanedBooks();

    /**
     * Adds a book to the user's list of loaned books.
     *
     * @param book the book to be added to the loaned books list.
     */
    void addLoanedBook(Book book);

    /**
     * Removes a book from the user's list of loaned books.
     *
     * @param book the book to be removed from the loaned books list.
     */
    void removeLoanedBook(Book book);

    /**
     * Retrieves the total number of books loaned by the user.
     *
     * @return the total number of books loaned by the user.
     */
    int getTotalBooksLoaned();

    /**
     * Retrieves the number of books currently loaned by the user.
     *
     * @return the number of books currently loaned by the user.
     */
    int getCurrentlyLoanedBooks();
}
