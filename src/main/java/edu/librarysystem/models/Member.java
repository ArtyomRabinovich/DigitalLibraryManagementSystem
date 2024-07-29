package edu.librarysystem.models;

import edu.librarysystem.interfaces.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Member class represents a member of the library system. It implements
 * the User interface.
 */
public class Member implements User {
    private static int lastId = 0;

    private final int id;
    private final String name;
    private final Date creationDate;
    private final List<Book> loanedBooks;
    private int totalBooksLoaned;
    private int currentlyLoanedBooks;

    /**
     * Constructs a new Member with the specified name.
     *
     * @param name the name of the member.
     */
    public Member(String name) {
        this.id = ++lastId;
        this.name = name;
        this.creationDate = new Date();
        this.loanedBooks = new ArrayList<>();
        this.totalBooksLoaned = 0;
        this.currentlyLoanedBooks = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLoanedBook(Book book) {
        loanedBooks.add(book);
        totalBooksLoaned++;
        currentlyLoanedBooks++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLoanedBook(Book book) {
        loanedBooks.remove(book);
        currentlyLoanedBooks--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalBooksLoaned() {
        return totalBooksLoaned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentlyLoanedBooks() {
        return currentlyLoanedBooks;
    }
}
