package edu.librarysystem.singleton;

import edu.librarysystem.factories.Factory;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.observers.LibraryChangeListener;

import java.util.*;

/**
 * Singleton class that represents the library system.
 * It manages the collection of library items (books) and members.
 */
public class Library {
    private static Library instance;
    private final Map<Integer, LibraryItem> items;
    private final Map<Integer, Member> members;
    private final Factory factory;

    private final List<LibraryChangeListener> changeListeners = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation.
     * Initializes the item and member collections and the factory.
     */
    private Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        factory = new Factory();
    }

    /**
     * Returns the singleton instance of the Library.
     *
     * @return the singleton instance.
     */
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    /**
     * Adds a change listener to the library.
     *
     * @param listener the listener to be added.
     */
    public void addChangeListener(LibraryChangeListener listener) {
        changeListeners.add(listener);
    }

    /**
     * Notifies all registered change listeners of a change in the library.
     */
    private void notifyChangeListeners() {
        for (LibraryChangeListener listener : changeListeners) {
            listener.onLibraryChanged();
        }
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
        Book book = factory.createBook(title, author, pages, isbn, yearPublished);
        items.put(book.getId(), book);
        notifyChangeListeners();
    }

    /**
     * Deletes a book from the library if it is available.
     *
     * @param id the ID of the book to be deleted.
     * @return true if the book was deleted, false otherwise.
     */
    public boolean deleteBook(int id) {
        Book book = getBook(id);
        if (book != null && book.isAvailable()) {
            items.remove(id);
            notifyChangeListeners();
            return true;
        }
        return false;
    }

    /**
     * Retrieves a book from the library by its ID.
     *
     * @param id the ID of the book.
     * @return the book with the specified ID, or null if not found.
     */
    public Book getBook(int id) {
        LibraryItem item = items.get(id);
        if (item instanceof Book) {
            return (Book) item;
        }
        return null;
    }

    /**
     * Loans a book to a member.
     *
     * @param id the ID of the book to be loaned.
     * @param memberId the ID of the member borrowing the book.
     */
    public void loanBook(int id, int memberId) {
        Book book = getBook(id);
        if (book != null && book.isAvailable()) {
            Member member = getMember(memberId);
            if (member != null) {
                book.setAvailable(false);
                book.setLoanedTo(member);
                member.addLoanedBook(book);
                notifyChangeListeners();
            }
        }
    }

    /**
     * Returns a loaned book to the library.
     *
     * @param id the ID of the book to be returned.
     */
    public void returnBook(int id) {
        Book book = getBook(id);
        if (book != null && !book.isAvailable()) {
            Member member = book.getLoanedTo();
            if (member != null) {
                member.removeLoanedBook(book);
            }
            book.setAvailable(true);
            book.setLoanedTo(null);
            notifyChangeListeners();
        }
    }

    /**
     * Adds a new member to the library.
     *
     * @param name the name of the member.
     */
    public void addMember(String name) {
        Member member = factory.createMember(name);
        members.put(member.getId(), member);
        notifyChangeListeners();
    }

    /**
     * Deletes a member from the library if they have no currently loaned books.
     *
     * @param id the ID of the member to be deleted.
     * @return true if the member was deleted, false otherwise.
     */
    public boolean deleteMember(int id) {
        Member member = getMember(id);
        if (member != null && member.getCurrentlyLoanedBooks() == 0) {
            members.remove(id);
            notifyChangeListeners();
            return true;
        }
        return false;
    }

    /**
     * Retrieves a member from the library by their ID.
     *
     * @param id the ID of the member.
     * @return the member with the specified ID, or null if not found.
     */
    public Member getMember(int id) {
        return members.get(id);
    }

    /**
     * Clones a book in the library.
     * The cloned book will have a new unique ID.
     *
     * @param id the ID of the book to be cloned.
     */
    public void cloneBook(int id) {
        Book book = getBook(id);
        if (book != null && book.isAvailable()) {
            Book clonedBook = book.clone();
            items.put(clonedBook.getId(), clonedBook);
            notifyChangeListeners();
        }
    }

    /**
     * Retrieves all books in the library.
     *
     * @return a list of all books.
     */
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (LibraryItem item : items.values()) {
            if (item instanceof Book) {
                allBooks.add((Book) item);
            }
        }
        return allBooks;
    }

    /**
     * Retrieves all members of the library.
     *
     * @return a list of all members.
     */
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    /**
     * Resets the singleton instance for testing purposes.
     */
    public static void resetInstance() {
        instance = null;
    }
}
