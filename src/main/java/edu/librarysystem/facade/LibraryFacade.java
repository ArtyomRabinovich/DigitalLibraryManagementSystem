package edu.librarysystem.facade;

import edu.librarysystem.commands.*;
import edu.librarysystem.factories.Factory;
import edu.librarysystem.interfaces.Command;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;
import edu.librarysystem.observers.LoanSystem;
import edu.librarysystem.singleton.Library;
import java.util.Comparator;
import java.util.List;

public class LibraryFacade {
    private final LibrarySystem librarySystem;
    private final LibraryItemService libraryItemService;
    private final UserService userService;

    public LibraryFacade(LibrarySystem librarySystem, LibraryItemService libraryItemService, UserService userService) {
        this.librarySystem = librarySystem;
        this.libraryItemService = libraryItemService;
        this.userService = userService;
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        Command addBookCommand = new AddLibraryItemCommand(libraryItemService, title, author, pages, isbn, yearPublished);
        librarySystem.addCommand(addBookCommand);
    }

    public  void deleteBook(int id) {
        Command deleteBookCommand = new DeleteLibraryItemCommand(libraryItemService, id);
        librarySystem.addCommand(deleteBookCommand);
    }

    public  void loanBook(int bookId, int memberId) {
        Command loanBookCommand = new LoanLibraryItemCommand(libraryItemService, bookId, memberId);
        librarySystem.addCommand(loanBookCommand);
    }

    public  void returnBook(int id) {
        Command returnBookCommand = new ReturnLibraryItemCommand(libraryItemService, id);
        librarySystem.addCommand(returnBookCommand);
    }

    public  void addMember(String name) {
        Command addMemberCommand = new AddMemberCommand(userService, name);
        librarySystem.addCommand(addMemberCommand);
    }

    public void deleteMember(int id) {
        Command deleteMemberCommand = new DeleteMemberCommand(userService, id);
        librarySystem.addCommand(deleteMemberCommand);
    }

    public String getLibrarySummary() {
        return Library.getInstance().getSummary();
    }

    public Book duplicateBook(int id) {
        LibraryItem item = libraryItemService.getItem(id);
        if (item instanceof Book) {
            return Library.getInstance().cloneBook(id);
        }
        return null;
    }

    public boolean isBookAvailable(int id) {
        LibraryItem item = libraryItemService.getItem(id);
        if (item instanceof Book book) {
            return book.isAvailable();
        }
        return false;
    }

    public Member getLoaningMember(int id) {
        LibraryItem item = libraryItemService.getItem(id);
        if (item instanceof Book book) {
            return book.getLoanedTo();
        }
        return null;
    }

    public Member getMember(int id) {
        return userService.getMember(id);
    }

    public void shutdown() {
        librarySystem.shutdown();
    }

    public List<Book> getBooksSortedBy(Comparator<Book> comparator) {
        return Library.getInstance().getBooksSortedBy(comparator);
    }

    public List<Member> getMembersSortedBy(Comparator<Member> comparator) {
        return Library.getInstance().getMembersSortedBy(comparator);
    }
}
