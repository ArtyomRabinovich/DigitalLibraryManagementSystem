package edu.librarysystem.interfaces;

import edu.librarysystem.models.Member;

/**
 * The LibraryItem interface provides a blueprint for library items,
 * defining the essential methods that must be implemented by any class
 * representing a library item.
 */
public interface LibraryItem {

    /**
     * Retrieves the title of the library item.
     *
     * @return the title of the item.
     */
    String getTitle();

    /**
     * Retrieves the author of the library item.
     *
     * @return the author of the item.
     */
    String getAuthor();

    /**
     * Retrieves the number of pages in the library item.
     *
     * @return the number of pages in the item.
     */
    int getPages();

    /**
     * Checks if the library item is currently available for loan.
     *
     * @return true if the item is available, false otherwise.
     */
    boolean isAvailable();

    /**
     * Sets the availability status of the library item.
     *
     * @param available the new availability status to set.
     */
    void setAvailable(boolean available);

    /**
     * Retrieves the member to whom the library item is currently loaned.
     *
     * @return the member to whom the item is loaned, or null if the item is not loaned.
     */
    Member getLoanedTo();

    /**
     * Sets the member to whom the library item is loaned.
     *
     * @param loanedTo the member to whom the item is being loaned.
     */
    void setLoanedTo(Member loanedTo);
}
