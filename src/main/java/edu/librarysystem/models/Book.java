package edu.librarysystem.models;

import edu.librarysystem.interfaces.LibraryItem;

/**
 * The Book class represents a book in the library system. It implements
 * the LibraryItem interface and is cloneable.
 */
public class Book implements LibraryItem, Cloneable {
    private static int lastId = 0;

    private int id;
    private final String title;
    private final String author;
    private final int pages;
    private boolean available;
    private final String isbn;
    private final int yearPublished;
    private Member loanedTo;

    /**
     * Constructs a new Book with the specified title, author, pages, ISBN, and year published.
     *
     * @param title the title of the book.
     * @param author the author of the book.
     * @param pages the number of pages in the book.
     * @param isbn the ISBN of the book.
     * @param yearPublished the year the book was published.
     */
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

    /**
     * Retrieves the unique identifier of the book.
     *
     * @return the unique ID of the book.
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPages() {
        return pages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Member getLoanedTo() {
        return loanedTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoanedTo(Member loanedTo) {
        this.loanedTo = loanedTo;
    }

    /**
     * Creates and returns a copy of this book with a new unique ID.
     *
     * @return a clone of this book.
     */
    @Override
    public Book clone() {
        try {
            Book cloned = (Book) super.clone();
            cloned.id = ++lastId; // Assign a new unique ID
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Retrieves the ISBN of the book.
     *
     * @return the ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Retrieves the year the book was published.
     *
     * @return the year the book was published.
     */
    public int getYearPublished() {
        return yearPublished;
    }
}
