package edu.librarysystem.models;

import edu.librarysystem.interfaces.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member implements User {
    private static int lastId = 0;

    private int id;
    private String name;
    private Date creationDate;
    private List<Book> loanedBooks;
    private int totalBooksLoaned;
    private int currentlyLoanedBooks;

    public Member(String name) {
        this.id = ++lastId;
        this.name = name;
        this.creationDate = new Date();
        this.loanedBooks = new ArrayList<>();
        this.totalBooksLoaned = 0;
        this.currentlyLoanedBooks = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    @Override
    public void addLoanedBook(Book book) {
        loanedBooks.add(book);
        totalBooksLoaned++;
        currentlyLoanedBooks++;
    }

    @Override
    public void removeLoanedBook(Book book) {
        loanedBooks.remove(book);
        currentlyLoanedBooks--;
    }

    @Override
    public int getTotalBooksLoaned() {
        return totalBooksLoaned;
    }

    @Override
    public int getCurrentlyLoanedBooks() {
        return currentlyLoanedBooks;
    }
}
