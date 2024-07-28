package edu.librarysystem.interfaces;

import edu.librarysystem.models.Book;
import java.util.Date;
import java.util.List;

public interface User {
    int getId();
    String getName();
    Date getCreationDate();
    List<Book> getLoanedBooks();
    void addLoanedBook(Book book);
    void removeLoanedBook(Book book);
    int getTotalBooksLoaned();
    int getCurrentlyLoanedBooks();
}
