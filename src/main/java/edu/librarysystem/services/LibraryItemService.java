package edu.librarysystem.services;
import edu.librarysystem.interfaces.LibraryItem;
import edu.librarysystem.models.Book;
import edu.librarysystem.singleton.Library;

public class LibraryItemService {
    private final Library library = Library.getInstance();

    public LibraryItemService() {
    }

    public void addItem(String title, String author, int pages, String isbn, int yearPublished) {
        library.addBook(title, author, pages, isbn, yearPublished);
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
    public void cloneBook(int id){
        library.cloneBook(id);
    }
}
