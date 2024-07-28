package edu.librarysystem.singleton;

import edu.librarysystem.factories.Factory;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.observers.Observer;
import edu.librarysystem.comparators.*;

import java.util.*;

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

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(LibraryItem item) {
        for (Observer observer : observers) {
            observer.update(item);
        }
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        Book book = factory.createBook(title, author, pages, isbn, yearPublished);
        items.put(book.getId(), book);
        System.out.println("Added book: " + book.getTitle());
        notifyObservers(book);
    }

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
                System.out.println("Loaned book with ID: " + id + " to member ID: " + memberId);
                notifyObservers(book);
            } else {
                System.out.println("Member with ID " + memberId + " not found.");
            }
        } else {
            System.out.println("Book with ID " + id + " is not available.");
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
            System.out.println("Returned book with ID: " + id);
            notifyObservers(book);
        } else {
            System.out.println("Book with ID " + id + " is not currently loaned out.");
        }
    }

    public void addMember(String name) {
        Member member = factory.createMember(name);
        members.put(member.getId(), member);
        System.out.println("Added member: " + member.getName());
    }

    public void deleteMember(int id) {
        Member member = members.remove(id);
        if (member != null) {
            System.out.println("Deleted member with ID: " + id);
        } else {
            System.out.println("Member with ID " + id + " not found.");
        }
    }

    public Member getMember(int id) {
        return members.get(id);
    }

    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Items:\n");
        for (LibraryItem item : items.values()) {
            if (item instanceof Book) {
                Book book = (Book) item;
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

    public Book cloneBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            return book.clone();
        }
        return null;
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

    public List<Book> getBooksSortedBy(Comparator<Book> comparator) {
        List<Book> sortedBooks = getAllBooks();
        sortedBooks.sort(comparator);
        return sortedBooks;
    }

    public List<Member> getMembersSortedBy(Comparator<Member> comparator) {
        List<Member> sortedMembers = getAllMembers();
        sortedMembers.sort(comparator);
        return sortedMembers;
    }
}
