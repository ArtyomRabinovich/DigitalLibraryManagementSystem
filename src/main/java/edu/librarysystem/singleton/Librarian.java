package edu.librarysystem.singleton;

import edu.librarysystem.facade.LibraryFacade;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;


public class Librarian {
    private static Librarian instance;
    private final LibraryFacade libraryFacade;
    private final LibrarySystem librarySystem;

    private Librarian() {
        librarySystem = new LibrarySystem();
        LibraryItemService libraryItemService = new LibraryItemService();
        UserService userService = new UserService();
        this.libraryFacade = new LibraryFacade(librarySystem, libraryItemService, userService);
    }

    public static Librarian getInstance() {
        if (instance == null) {
            instance = new Librarian();
        }
        return instance;
    }

    public void addMember(String name) {
        libraryFacade.addMember(name);
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        libraryFacade.addBook(title, author, pages, isbn, yearPublished);
    }

    public void loanBook(int bookId, int memberId) {
        libraryFacade.loanBook(bookId, memberId);
    }

    public void returnBook(int bookId) {
        libraryFacade.returnBook(bookId);
    }

    public void deleteBook(int bookId) {
        libraryFacade.deleteBook(bookId);
    }

    public void deleteMember(int memberId) {
        libraryFacade.deleteMember(memberId);
    }


    public void duplicateBook(int bookId) {
         libraryFacade.duplicateBook(bookId);
    }
}
