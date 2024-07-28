package edu.librarysystem.models;

import edu.librarysystem.interfaces.LibraryItem;

public class Book implements LibraryItem, Cloneable {
    private static int lastId = 0;

    private int id;
    private String title;
    private String author;
    private int pages;
    private boolean available;
    private String isbn;
    private int yearPublished;
    private Member loanedTo;

    public Book(String title, String author, int pages, String isbn, int yearPublished) {
        this.id = ++lastId;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.available = true;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.loanedTo = null;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public int getPages() {
        return pages;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public Member getLoanedTo() {
        return loanedTo;
    }

    @Override
    public void setLoanedTo(Member loanedTo) {
        this.loanedTo = loanedTo;
    }

    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can’t happen
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYearPublished() {
        return yearPublished;
    }
}
