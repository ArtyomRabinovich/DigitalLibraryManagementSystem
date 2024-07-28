package edu.librarysystem.controllers;

import edu.librarysystem.facade.LibraryFacade;

public class MainController {
    private AddBookController addBookController;
    private DeleteBookController deleteBookController;
    private LoanBookController loanBookController;
    private ReturnBookController returnBookController;
    private AddMemberController addMemberController;
    private DeleteMemberController deleteMemberController;
    private LibraryFacade libraryFacade;

    public MainController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
        initialize();
    }

    public void initialize() {
        addBookController = new AddBookController(libraryFacade);
        deleteBookController = new DeleteBookController(libraryFacade);
        loanBookController = new LoanBookController(libraryFacade);
        returnBookController = new ReturnBookController(libraryFacade);
        addMemberController = new AddMemberController(libraryFacade);
        deleteMemberController = new DeleteMemberController(libraryFacade);
    }

    public String getLibrarySummary() {
        return libraryFacade.getLibrarySummary();
    }

    public void addBook(String title, String author, int pages, String isbn, int yearPublished) {
        addBookController.addBook(title, author, pages, isbn, yearPublished);
    }

    public void deleteBook(int bookId) {
        deleteBookController.deleteBook(bookId);
    }

    public void loanBook(int bookId, int memberId) {
        loanBookController.loanBook(bookId, memberId);
    }

    public void returnBook(int bookId) {
        returnBookController.returnBook(bookId);
    }

    public void addMember(String name) {
        addMemberController.addMember(name);
    }

    public void deleteMember(int memberId) {
        deleteMemberController.deleteMember(memberId);
    }
}
