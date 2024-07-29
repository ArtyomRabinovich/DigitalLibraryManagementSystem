package edu.librarysystem.singleton;

import edu.librarysystem.factories.Factory;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.observers.Observer;

import java.util.*;

/**
 * The Library class is a singleton that manages library items and members.
 * It provides methods to add, delete, loan, and return books, as well as manage members.
 */
public class Library {
    private static Library instance;
    private final Map<Integer, LibraryItem> items;
    private final Map<Integer, Member> members;
    private final List<Observer> observers;
    private final Factory factory;

    private Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        observers = new ArrayList<>();
        factory = new Factory();  // Initialize the Factory
    }

    /**
     * Returns the singleton instance of the Library.
     *
     * @return the singleton instance of the Library.
     */
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to be added.
     */
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to be removed.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers about a change in a library item.
     *
     * @param item the library item that has changed.
     */
    public void notifyObservers(LibraryItem item) {
        for (Observer observer : observers) {
            observer.update(item);
        }
    }

    /**
     * Adds a book to the library.
     *
     * @param title          the title of the book.
     * @param author         the author of the book.
     * @param pages          the number of pages in the book.
     * @param isbn           the ISBN of the book.
     * @param yearPublished  the year the book was published.
     */
    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        Book book = factory.createBook(title, author, pages, isbn, yearPublished);
        items.put(book.getId(), book);
        System.out.println("Added book: " + book.getTitle());
        notifyObservers(book);
    }

    /**
     * Deletes a book from the library.
     *
     * @param id the ID of the book to be deleted.
     */
    public void deleteBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            items.remove(id);
            System.out.println("Deleted book with ID: " + id);
            notifyObservers(book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book to be retrieved.
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
     * @param id       the ID of the book to be loaned.
     * @param memberId the ID of the member to loan the book to.
     */
    public void loanBook(int id, int memberId) {
        Book book = getBook(id);
        if (book != null && book.isAvailable()) {
            Member member = getMember(memberId);
            if (member != null) {
                book.setAvailable(false);
                book.setLoanedTo(member);
                member.addLoanedBook(book);
                System.out.println("Loaned book with ID: " + id + " to member ID: " + memberId);
                notifyObservers(book);
            } else {
                System.out.println("Member with ID " + memberId + " not found.");
            }
        } else {
            System.out.println("Book with ID " + id + " is not available.");
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
            System.out.println("Returned book with ID: " + id);
            notifyObservers(book);
        } else {
            System.out.println("Book with ID " + id + " is not currently loaned out.");
        }
    }

    /**
     * Adds a member to the library.
     *
     * @param name the name of the member to be added.
     */
    public void addMember(String name) {
        Member member = factory.createMember(name);
        members.put(member.getId(), member);
        System.out.println("Added member: " + member.getName());
    }

    /**
     * Deletes a member from the library.
     *
     * @param id the ID of the member to be deleted.
     */
    public void deleteMember(int id) {
        Member member = members.remove(id);
        if (member != null) {
            System.out.println("Deleted member with ID: " + id);
        } else {
            System.out.println("Member with ID " + id + " not found.");
        }
    }

    /**
     * Retrieves a member by their ID.
     *
     * @param id the ID of the member to be retrieved.
     * @return the member with the specified ID, or null if not found.
     */
    public Member getMember(int id) {
        return members.get(id);
    }

    /**
     * Returns a summary of the library's items and members.
     *
     * @return a summary string of the library's items and members.
     */
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Items:\n");
        for (LibraryItem item : items.values()) {
            if (item instanceof Book book) {
                summary.append("Title: ").append(book.getTitle())
                        .append(", Author: ").append(book.getAuthor())
                        .append(", Available: ").append(book.isAvailable())
                        .append(", Loaned to: ").append(book.getLoanedTo() != null ? book.getLoanedTo().getName() : "N/A")
                        .append("\n");
            }
        }
        summary.append("Members:\n");
        for (Member member : members.values()) {
            summary.append("Name: ").append(member.getName())
                    .append(", Total Books Loaned: ").append(member.getTotalBooksLoaned())
                    .append(", Currently Loaned Books: ").append(member.getCurrentlyLoanedBooks())
                    .append("\n");
        }
        return summary.toString();
    }

    /**
     * Clones a book by its ID.
     *
     * @param id the ID of the book to be cloned.
     * @return a clone of the book, or null if the book was not found.
     */
    public Book cloneBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            return book.clone();
        }
        return null;
    }

    /**
     * Retrieves all books in the library.
     *
     * @return a list of all books in the library.
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
     * @return a list of all members of the library.
     */
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    /**
     * Retrieves books sorted by a given comparator.
     *
     * @param comparator the comparator to sort the books.
     * @return a list of books sorted by the given comparator.
     */
    public List<Book> getBooksSortedBy(Comparator<Book> comparator) {
        List<Book> sortedBooks = getAllBooks();
        sortedBooks.sort(comparator);
        return sortedBooks;
    }

    /**
     * Retrieves members sorted by a given comparator.
     *
     * @param comparator the comparator to sort the members.
     * @return a list of members sorted by the given comparator.
     */
    public List<Member> getMembersSortedBy(Comparator<Member> comparator) {
        List<Member> sortedMembers = getAllMembers();
        sortedMembers.sort(comparator);
        return sortedMembers;
    }
}
