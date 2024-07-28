package edu.librarysystem.services;

import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.observers.LoanSystem;
import edu.librarysystem.singleton.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryItemService {
    private final Library library = Library.getInstance();
    private LoanSystem loanSystem;

    public LibraryItemService() {}

    public void setLoanSystem(LoanSystem loanSystem) {
        this.loanSystem = loanSystem;
    }

    public void addItem(String title, String author, int pages, String isbn, int yearPublished) {
            library.addBook(title, author, pages, isbn, yearPublished);
            Book item = new Book(title, author, pages, isbn, yearPublished);
            loanSystem.update(item);
        }


    public void deleteItem(int id) {
        library.deleteBook(id);
    }

    public void loanItem(int id, int memberId) {
        library.loanBook(id, memberId);
    }

    public void returnItem(int id) {
        library.returnBook(id);
    }

    public LibraryItem getItem(int id) {
        return library.getBook(id);
    }

    public List<LibraryItem> getAllItems() {
        return new ArrayList<>(library.getAllBooks());
    }
}
