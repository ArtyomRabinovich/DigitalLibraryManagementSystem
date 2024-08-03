package edu.librarysystem.singleton;

import edu.librarysystem.facade.LibraryFacade;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;

/**
 * Singleton class that represents the librarian responsible for managing library operations.
 * It provides a simplified interface for library operations using the LibraryFacade.
 */
public class Librarian {
    private static Librarian instance;
    private final LibraryFacade libraryFacade;
    private final LibrarySystem librarySystem;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the LibrarySystem, LibraryItemService, UserService, and LibraryFacade.
     */
    private Librarian() {
        librarySystem = new LibrarySystem();
        LibraryItemService libraryItemService = new LibraryItemService();
        UserService userService = new UserService();
        this.libraryFacade = new LibraryFacade(librarySystem, libraryItemService, userService);
    }

    /**
     * Returns the singleton instance of the Librarian.
     *
     * @return the singleton instance.
     */
    public static Librarian getInstance() {
        if (instance == null) {
            instance = new Librarian();
        }
        return instance;
    }

    /**
     * Adds a new member to the library.
     *
     * @param name the name of the member.
     */
    public void addMember(String name) {
        libraryFacade.addMember(name);
    }

    /**
     * Adds a new book to the library.
     *
     * @param title the title of the book.
     * @param author the author of the book.
     * @param pages the number of pages in the book.
     * @param isbn the ISBN of the book.
     * @param yearPublished the year the book was published.
     */
    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        libraryFacade.addBook(title, author, pages, isbn, yearPublished);
    }

    /**
     * Loans a book to a member.
     *
     * @param bookId the ID of the book to be loaned.
     * @param memberId the ID of the member borrowing the book.
     */
    public void loanBook(int bookId, int memberId) {
        libraryFacade.loanBook(bookId, memberId);
    }

    /**
     * Returns a loaned book to the library.
     *
     * @param bookId the ID of the book to be returned.
     */
    public void returnBook(int bookId) {
        libraryFacade.returnBook(bookId);
    }

    /**
     * Deletes a book from the library.
     *
     * @param bookId the ID of the book to be deleted.
     * @return true if the book was deleted, false otherwise.
     */
    public boolean deleteBook(int bookId) {
        return libraryFacade.deleteBook(bookId);
    }

    /**
     * Deletes a member from the library.
     *
     * @param memberId the ID of the member to be deleted.
     * @return true if the member was deleted, false otherwise.
     */
    public boolean deleteMember(int memberId) {
        return libraryFacade.deleteMember(memberId);
    }

    /**
     * Duplicates a book in the library.
     * The duplicated book will have a new unique ID.
     *
     * @param bookId the ID of the book to be duplicated.
     */
    public void duplicateBook(int bookId) {
        libraryFacade.duplicateBook(bookId);
    }
}
