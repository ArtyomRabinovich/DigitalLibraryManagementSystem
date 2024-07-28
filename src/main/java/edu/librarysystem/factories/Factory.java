package edu.librarysystem.factories;

import edu.librarysystem.models.Book;
import edu.librarysystem.models.Member;

public class Factory {
    public Book createBook(String title, String author, int pages, String isbn, int yearPublished) {
        return new Book(title, author, pages, isbn, yearPublished);
    }

    public Member createMember(String name) {
        return new Member(name);
    }
}
