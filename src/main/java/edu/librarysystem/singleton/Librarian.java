package edu.librarysystem.singleton;

import edu.librarysystem.facade.LibraryFacade;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;
import edu.librarysystem.factories.Factory;
import edu.librarysystem.observers.LoanSystem;
import edu.librarysystem.observers.BookObserver;
import java.util.Comparator;
import java.util.List;

public class Librarian {
    private static Librarian instance;
    private LibraryFacade libraryFacade;

    private Librarian() {
        LibraryItemService libraryItemService = new LibraryItemService();
        UserService userService = new UserService();

        // Create the LoanSystem and specific observers
        LoanSystem loanSystem = new LoanSystem();
        BookObserver bookObserver = new BookObserver(userService);

        // Add the specific observers to the LoanSystem
        loanSystem.addObserver(bookObserver);

        // Set the LoanSystem in the LibraryItemService
        libraryItemService.setLoanSystem(loanSystem);

        this.libraryFacade = new LibraryFacade(new LibrarySystem(), libraryItemService, userService, loanSystem, new Factory());
    }

    public static Librarian getInstance() {
        if (instance == null) {
            instance = new Librarian();
        }
        return instance;
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        libraryFacade.addBook(title, author, pages, isbn, yearPublished);
    }

    public void deleteBook(int bookId) {
        libraryFacade.deleteBook(bookId);
    }

    public void loanBook(int bookId, int memberId) {
        libraryFacade.loanBook(bookId, memberId);
    }

    public void returnBook(int bookId) {
        libraryFacade.returnBook(bookId);
    }

    public void addMember(String name) {
        libraryFacade.addMember(name);
    }

    public void deleteMember(int memberId) {
        libraryFacade.deleteMember(memberId);
    }

    public String getLibrarySummary() {
        return libraryFacade.getLibrarySummary();
    }

    public Book duplicateBook(int bookId) {
        return libraryFacade.duplicateBook(bookId);
    }

    public boolean isBookAvailable(int bookId) {
        return libraryFacade.isBookAvailable(bookId);
    }

    public Member getLoaningMember(int bookId) {
        return libraryFacade.getLoaningMember(bookId);
    }

    public Member getMember(int memberId) {
        return libraryFacade.getMember(memberId);
    }

    public void shutdownLibrary() {
        libraryFacade.shutdown();
    }

    // New methods to get sorted lists of books and members
    public List<Book> getBooksSortedBy(Comparator<Book> comparator) {
        return libraryFacade.getBooksSortedBy(comparator);
    }

    public List<Member> getMembersSortedBy(Comparator<Member> comparator) {
        return libraryFacade.getMembersSortedBy(comparator);
    }
}
