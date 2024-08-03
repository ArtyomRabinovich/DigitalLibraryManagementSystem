package edu.librarysystem.facade;

import edu.librarysystem.commands.*;
import edu.librarysystem.interfaces.Command;
import edu.librarysystem.invoker.LibrarySystem;
import edu.librarysystem.services.LibraryItemService;
import edu.librarysystem.services.UserService;


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

    public void duplicateBook(int id) {
        DuplicateBookCommand duplicateBookCommand = new DuplicateBookCommand(libraryItemService, id);
        librarySystem.addCommand(duplicateBookCommand);
    }


}
