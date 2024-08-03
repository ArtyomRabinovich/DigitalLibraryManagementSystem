package edu.librarysystem.singleton;

import edu.librarysystem.factories.Factory;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.observers.LibraryChangeListener;

import java.util.*;

public class Library {
    private static Library instance;
    private final Map<Integer, LibraryItem> items;
    private final Map<Integer, Member> members;
    private final Factory factory;

    // List of listeners to notify of changes
    private final List<LibraryChangeListener> changeListeners = new ArrayList<>();

    private Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        factory = new Factory();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addChangeListener(LibraryChangeListener listener) {
        changeListeners.add(listener);
    }

    private void notifyChangeListeners() {
        for (LibraryChangeListener listener : changeListeners) {
            listener.onLibraryChanged();
        }
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        Book book = factory.createBook(title, author, pages, isbn, yearPublished);
        items.put(book.getId(), book);
        notifyChangeListeners();
    }

    public void deleteBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            items.remove(id);
            notifyChangeListeners();
        }
    }

    public Book getBook(int id) {
        LibraryItem item = items.get(id);
        if (item instanceof Book) {
            return (Book) item;
        }
        return null;
    }

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

    public void addMember(String name) {
        Member member = factory.createMember(name);
        members.put(member.getId(), member);
        notifyChangeListeners();
    }

    public void deleteMember(int id) {
        Member member = members.remove(id);
        if (member != null) {
            notifyChangeListeners();
        }
    }

    public Member getMember(int id) {
        return members.get(id);
    }

    public void cloneBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            Book clonedBook = book.clone();
            items.put(clonedBook.getId(), clonedBook);
            notifyChangeListeners();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();
        for (LibraryItem item : items.values()) {
            if (item instanceof Book) {
                allBooks.add((Book) item);
            }
        }
        return allBooks;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }
}
