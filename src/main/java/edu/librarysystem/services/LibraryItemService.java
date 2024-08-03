package edu.librarysystem.services;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.singleton.Library;

/**
 * The {@code LibraryItemService} class provides services to manage library items
 * including adding, deleting, loaning, returning, and cloning books.
 */
public class LibraryItemService {
    private final Library library = Library.getInstance();

    /**
     * Constructs a new {@code LibraryItemService}.
     */
    public LibraryItemService() {
    }

    /**
     * Adds a new book to the library.
     *
     * @param title         the title of the book
     * @param author        the author of the book
     * @param pages         the number of pages in the book
     * @param isbn          the ISBN of the book
     * @param yearPublished the year the book was published
     */
    public void addItem(String title, String author, int pages, String isbn, int yearPublished) {
        library.addBook(title, author, pages, isbn, yearPublished);
    }

    /**
     * Deletes a book from the library.
     *
     * @param id the ID of the book to be deleted
     */
    public void deleteItem(int id) {
        library.deleteBook(id);
    }

    /**
     * Loans a book to a member.
     *
     * @param id       the ID of the book to be loaned
     * @param memberId the ID of the member to loan the book to
     */
    public void loanItem(int id, int memberId) {
        library.loanBook(id, memberId);
    }

    /**
     * Returns a loaned book to the library.
     *
     * @param id the ID of the book to be returned
     */
    public void returnItem(int id) {
        library.returnBook(id);
    }

    /**
     * Retrieves a book from the library.
     *
     * @param id the ID of the book to retrieve
     * @return the {@code LibraryItem} representing the book, or {@code null} if not found
     */
    public LibraryItem getItem(int id) {
        return library.getBook(id);
    }

    /**
     * Clones a book in the library.
     *
     * @param id the ID of the book to clone
     */
    public void cloneBook(int id) {
        library.cloneBook(id);
    }
}
